![graphdb](http://neo4j.com/wp-content/themes/neo4jweb/assets/images/icons/icon-neo@2x.jpg)

# Objectif du TP

Montrer en quoi la base de données graphe Neo4j a été déterminante
dans l'affaire du Panama Leak.

Ce TP est issu de "Graph Gist" suivant : http://neo4j.com/graphgist/ec65c2fa-9d83-4894-bc1e-98c475c7b57a

Les Graph Gists sont des documents markdown améliorés permettant de présenter un
cas d'usage autour de la base de donnée Neo4j un intégrant une instance de base de
données et des requêtes avec le document.

# Déroulement du TP

1. Introduction à Neo4j
2. Insertion du jeu de données
3. Requêtes de découverte
4. Inspectons et résolvons l'affaire Panama Leak !

# Introduction à Neo4j

Neo4j est une base de donnée NoSQL de type graphe. C'est-à-dire que les données
dans la base de données sont stockées sous forme de graphe que l'on va pouvoir
requêter et traverser.

La base de données orientée graphe s'appuie sur la théorie des graphe. Un graphe
se définie comme un ensemble de noeuds (nodes) et un ensemble de liens (edges ou
  relationships) entre ces neouds.

Dans Neo4j :
* les neouds et les relations portent des données sous forme d'un ensemble de clé /
valeur, on parle de "property graph"
* les neouds peuvent avoir un ou plusieurs labels pour les caractériser (par exemple
  un noeud peut avoir les labels "User" et "Admin" en même temps)
* les relations ont un sens, c'est à dire un noeud de départ et un neoud de finie
* les relations sont caractérisées par un type, par exemple "IS_FRIEND_WITH"
* il existe un langage de requête qui s'appelle Cypher

![property_graph](http://dev.assets.neo4j.com.s3.amazonaws.com/wp-content/uploads/property_graph_model.png)

# Insertion du jeu de données

Exécuter la requête Cypher suivante pour vider la base de données :

```
MATCH (n)
OPTIONAL MATCH (n)-[r]-()
DELETE n,r
```

Utiliser la requête suivante pour insérer les données dans la base de données :

```
// Create People
CREATE (Ilham_Aliyev:Person {first_name:'Ilham', last_name:'Aliyev', job:'President', Employer:'Azerbaijan', picture: 'http://bigbrowser.blog.lemonde.fr/files/2013/04/000_Par7450762-530x343.jpg'})
CREATE (Hassan_Gozal:Person {first_name:'Hassan', last_name:'Gozal', job:'Vice President', Employer:'Intersun Holding', picture: ''})
CREATE (Abdolbari_Gozal:Person {first_name:'Abdolbari', last_name:'Gozal', job:'President', Employer:'Intersun Holding', picture: 'http://www.azersun.az/news/news146.jpg'})
CREATE (Mehriban_Aliyeva:Person {first_name:'Mehriban', last_name:'Aliyeva', job:'Director', Employer:'Heydar Aliyev Foundation ', picture: 'http://www.mehriban-aliyeva.org//u/a/4/o.jpg'})
CREATE (Arzu_Aliyeva:Person {first_name:'Arzu', last_name:'Aliyeva', job:'', Employer:'', picture: 'http://legend.az/uploads/posts/2011-09/1315158828_arzi-aliyeva-008.jpg'})
CREATE (Leyla_Aliyeva:Person {first_name:'Leyla', last_name:'Aliyeva', job:'', Employer:'', picture: 'http://www2.pictures.zimbio.com/gi/Leyla+Aliyeva+Fly+Bakou+Exhibition+Launch+Nn74uXjDl_cl.jpg'})
CREATE (Heydar_Aliyev:Person {first_name:'Heydar', last_name:'Aliyev', job:'', Employer:'', picture: ''})
CREATE (Ridzuan_Salleh:Person {first_name:'Ridzuan', last_name:'Salleh', job:'', Employer:'', picture: ''})

// Create Addresses
CREATE (Al_Fairooz_Dubai_Marina:Address {address:'13 Al Fairooz Dubai Marina', city:'Dubai', country:'Dubai'})
CREATE (Portcullis_TrustNet_Chambers_POBox:Address {address:'Portcullis TrustNet Chambers P.O. Box 3444 Road Town', city:'Tortola', country:'Tortola'})
CREATE (UnitPL01A_Plaza_Level:Address {address:'Unit PL 01-A, Plaza Level, No. 45 Block A Medan Setia 1, Plaza Damansara, Damansara Heights, 50490', city:'Kuala Lumpur', country:'Kuala Lumpur'})
CREATE (Apartment_No1801_Dubai:Address {address:'Apartment No. 1801 Dubai Marina Lerev Residential', city:'Dubai', country:'Dubai'})
CREATE (Apartment_No1802_Dubai:Address {address:'Apartment No. 1802 Dubai Marina Lerev Residential', city:'Dubai', country:'Dubai'})
CREATE (Villa22_Gate51_Avenue6:Address {address:'Villa 22 Gate 51 Avenue 6 Jannusan 504', city:'', country:''})
CREATE (Prospekt_Kutuzonskiy:Address {address:'444, 4/2 Prospekt Kutuzonskiy', city:'Moscow', country:'Moscow'})
CREATE (Honest_Bright_Company_Ltd:Address {address:'Honest & Bright Company Ltd. Office 53-54, Construction 3 3-rd Tverskaya-Yamskaya Street, Bld. 12', city:'Moscow', country:'Moscow'})
CREATE (PO_Box_117920:Address {address:'P.O. Box 117920 Jebel Ali Free Zone', city:'Dubai', country:'Dubai'})
CREATE (Shenton_Way_DBS:Address {address:'6 Shenton Way #14-01 DBS Building Tower One Singapore 068809', city:'Singapore', country:'Singapore'})

// Create Companies
CREATE (Harvard_Management_Limited:Company {name:'Harvard Management Limited', form:'Standard International Company', incorporation:'07/11/2008', status:'Active', link: 'http://offshoreleaks.icij.org/nodes/166436'})
CREATE (LaBelleza_Holdings_Limited:Company {name:'LaBelleza Holdings Limited', form:'Business Company Limited by Shares', incorporation:'07/11/2008', status:'Active', link: 'http://offshoreleaks.icij.org/nodes/166434'})
CREATE (Arbor_Investments_Limited:Company {name:'Arbor Investments Limited', form:'Business Company Limited by Shares', incorporation:'07/11/2008', status:'Active', link: 'http://offshoreleaks.icij.org/nodes/166435'})
CREATE (Portcullis_Trustnet:Company {name:'Portcullis Trustnet', form:'OFFSHORE SERVICE PROVIDER', incorporation:'', status:'', link: 'http://offshoreleaks.icij.org/nodes/54662'})
CREATE (Naziq_and_Partners:Company {name:'Naziq & Partners', form:'', incorporation:'', status:'', link: 'http://offshoreleaks.icij.org/nodes/294050'})
CREATE (Crovelent_Holdings_LTD:Company {name:'Crovelent Holdings LTD.', form:'Business Company Limited by Shares', incorporation:'29/03/2007', status:'Active', link: 'http://offshoreleaks.icij.org/nodes/204584'})
CREATE (Rosamund_International_Ltd:Company {name:'Rosamund International Ltd', form:'Standard International Company', incorporation:'08/11/2002', status:'Inactive', link: 'http://offshoreleaks.icij.org/nodes/138523'})
CREATE (DBS_Trustee_Limited:Company {name:'DBS Trustee Limited', form:'', incorporation:'', status:'', link: 'http://offshoreleaks.icij.org/nodes/290319'})

// Create Relationships
CREATE (Ilham_Aliyev)-[:FAMILY]->(Mehriban_Aliyeva)
CREATE (Ilham_Aliyev)-[:FAMILY]->(Arzu_Aliyeva)
CREATE (Ilham_Aliyev)-[:FAMILY]->(Leyla_Aliyeva)
CREATE (Ilham_Aliyev)-[:FAMILY]->(Heydar_Aliyev)
CREATE (Mehriban_Aliyeva)-[:FAMILY]->(Arzu_Aliyeva)
CREATE (Mehriban_Aliyeva)-[:FAMILY]->(Leyla_Aliyeva)
CREATE (Mehriban_Aliyeva)-[:FAMILY]->(Heydar_Aliyev)
CREATE (Leyla_Aliyeva)-[:FAMILY]->(Arzu_Aliyeva)
CREATE (Leyla_Aliyeva)-[:FAMILY]->(Heydar_Aliyev)
CREATE (Arzu_Aliyeva)-[:FAMILY]->(Heydar_Aliyev)
CREATE (Hassan_Gozal)-[:FAMILY]->(Abdolbari_Gozal)
CREATE (Hassan_Gozal)-[:USES_ADDRESS]->(Al_Fairooz_Dubai_Marina)
CREATE (Harvard_Management_Limited)-[:USES_ADDRESS]->(Portcullis_TrustNet_Chambers_POBox)
CREATE (Naziq_and_Partners)-[:USES_ADDRESS]->(UnitPL01A_Plaza_Level)
CREATE (Arzu_Aliyeva)-[:USES_ADDRESS]->(Apartment_No1801_Dubai)
CREATE (Leyla_Aliyeva)-[:USES_ADDRESS]->(Apartment_No1802_Dubai)
CREATE (Ridzuan_Salleh)-[:USES_ADDRESS]->(Villa22_Gate51_Avenue6)
CREATE (Leyla_Aliyeva)-[:USES_ADDRESS]->(Prospekt_Kutuzonskiy)
CREATE (Mehriban_Aliyeva)-[:USES_ADDRESS]->(PO_Box_117920)
CREATE (Ilham_Aliyev)-[:USES_ADDRESS]->(PO_Box_117920)
CREATE (Rosamund_International_Ltd)-[:USES_ADDRESS]->(Portcullis_TrustNet_Chambers_POBox)
CREATE (DBS_Trustee_Limited)-[:USES_ADDRESS]->(Shenton_Way_DBS)
CREATE (LaBelleza_Holdings_Limited)-[:USES_ADDRESS]->(Portcullis_TrustNet_Chambers_POBox)
CREATE (Arbor_Investments_Limited)-[:USES_ADDRESS]->(Portcullis_TrustNet_Chambers_POBox)
CREATE (Crovelent_Holdings_LTD)-[:USES_ADDRESS]->(Honest_Bright_Company_Ltd)
CREATE (Portcullis_Trustnet)-[:IS_OFFSHORE_PROVIDER_OF]->(Harvard_Management_Limited)
CREATE (Portcullis_Trustnet)-[:IS_OFFSHORE_PROVIDER_OF]->(Naziq_and_Partners)
CREATE (Portcullis_Trustnet)-[:IS_OFFSHORE_PROVIDER_OF]->(LaBelleza_Holdings_Limited)
CREATE (Portcullis_Trustnet)-[:IS_OFFSHORE_PROVIDER_OF]->(Arbor_Investments_Limited)
CREATE (Portcullis_Trustnet)-[:IS_OFFSHORE_PROVIDER_OF]->(Rosamund_International_Ltd)
CREATE (Naziq_and_Partners)-[:IS_LINKED_TO {role:'Master Client', date:''}]->(Harvard_Management_Limited)
CREATE (Portcullis_Trustnet)-[:IS_LINKED_TO {role:'Records And Registers', date:''}]->(Harvard_Management_Limited)
CREATE (Hassan_Gozal)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(Harvard_Management_Limited)
CREATE (Ridzuan_Salleh)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(Harvard_Management_Limited)
CREATE (Leyla_Aliyeva)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(Harvard_Management_Limited)
CREATE (Leyla_Aliyeva)-[:IS_LINKED_TO {role:'Shareholder', date:'39759'}]->(Harvard_Management_Limited)
CREATE (Naziq_and_Partners)-[:IS_LINKED_TO {role:'Master Client', date:''}]->(LaBelleza_Holdings_Limited)
CREATE (Portcullis_Trustnet)-[:IS_LINKED_TO {role:'Records And Registers', date:''}]->(LaBelleza_Holdings_Limited)
CREATE (Hassan_Gozal)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(LaBelleza_Holdings_Limited)
CREATE (Ridzuan_Salleh)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(LaBelleza_Holdings_Limited)
CREATE (Leyla_Aliyeva)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(LaBelleza_Holdings_Limited)
CREATE (Leyla_Aliyeva)-[:IS_LINKED_TO {role:'Shareholder', date:'39759'}]->(LaBelleza_Holdings_Limited)
CREATE (Naziq_and_Partners)-[:IS_LINKED_TO {role:'Master Client', date:''}]->(Arbor_Investments_Limited)
CREATE (Portcullis_Trustnet)-[:IS_LINKED_TO {role:'Records And Registers', date:''}]->(Arbor_Investments_Limited)
CREATE (Arzu_Aliyeva)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(Arbor_Investments_Limited)
CREATE (Hassan_Gozal)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(Arbor_Investments_Limited)
CREATE (Ridzuan_Salleh)-[:IS_LINKED_TO {role:'Director', date:'39759'}]->(Arbor_Investments_Limited)
CREATE (Arzu_Aliyeva)-[:IS_LINKED_TO {role:'Shareholder', date:'39759'}]->(Arbor_Investments_Limited)
CREATE (Leyla_Aliyeva)-[:IS_LINKED_TO {role:'Shareholder', date:'39653'}]->(Crovelent_Holdings_LTD)
CREATE (Mehriban_Aliyeva)-[:IS_LINKED_TO {role:'Director Of', date:'37635'}]->(Rosamund_International_Ltd)
CREATE (Mehriban_Aliyeva)-[:IS_LINKED_TO {role:'Shareholder Of', date:'37635'}]->(Rosamund_International_Ltd)
CREATE (DBS_Trustee_Limited)-[:IS_LINKED_TO {role:'Master Client', date:''}]->(Rosamund_International_Ltd)
CREATE (Portcullis_Trustnet)-[:IS_LINKED_TO {role:'Records And Registers', date:''}]->(Rosamund_International_Ltd)
CREATE (Ilham_Aliyev)-[:IS_LINKED_TO {role:'Director', date:'37635'}]->(Rosamund_International_Ltd)
CREATE (Ilham_Aliyev)-[:IS_LINKED_TO {role:'Shareholder', date:'37635'}]->(Rosamund_International_Ltd)
```

# Requêtes de découverte

## Voir les Personnes

```
MATCH (p:Person) RETURN p
```

## Voir les entreprises

```
MATCH (c:Company) RETURN c
```

# Inspectons et résolvons l'affaire Panama Leak !

## Détailler les membres d'une famille

Dans la précédente requête, on note la présence d'un personnage important :
Ilham Aliyev, le président de l'Azebaïdjan.

Essayons de voir les membres de sa famille présents dans notre jeu de données :

```
MATCH (p:Person)-[:FAMILY]-(p2)
WHERE p.first_name = 'Ilham' AND p.last_name = 'Aliyev'
RETURN p, p2
```

En exécutant la requête on découvre la présence d'un cercle famillial autour du
président.

## Entreprises du président

```
MATCH (p:Person)-[]-(c:Company)
WHERE p.first_name = 'Ilham' AND p.last_name = 'Aliyev'
RETURN p, c
```

Le président peut avoir plusieurs rôles au sein d'une même entreprise, il est
possible d'aggréger ces derniers :

```
MATCH (p:Person)-[r]-(c:Company)
WHERE p.first_name = 'Ilham' AND p.last_name = 'Aliyev'
RETURN c, collect(r.role)
```

## Une affaire de famille ?

```
MATCH (p:Person)-[:FAMILY]-(p2)
MATCH (p)--(c:Company)
MATCH (p2)--(c2:Company)
WHERE p.first_name = 'Ilham' AND p.last_name = 'Aliyev'
RETURN p, p2, c, c2
```

Si le président n'est directement impliqué que dans une seule entreprise offshore,
on ne peut pas en dire autant du cercle famillial dans son ensemble.

## Le rôle des intermédiaires

```
MATCH (president:Person {first_name:'Ilham'})-[r*]->(account:Company)
MATCH (account)-[t]-(middlemen:Company)
RETURN account, middlemen, president
```

Le président n'est lié qu'avec une entreprise offshore, mais on se rend
compte que ce lien lui ouvert de nombreuses entreprises offshore, et que des liens
entre ces sociétés ont été créés (relations clients / fournisseurs, etc. )
