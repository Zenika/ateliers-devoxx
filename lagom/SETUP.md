## Prérequis pour dérouler les exercices

Pour ce TP, java 8 est requis ainsi que [SBT](http://www.scala-sbt.org/0.13/docs/Installing-sbt-on-Linux.html)
Prévoir l'installation de l'environnement avant la conférence, beaucoup de téléchargement à la première installation.

```bash
# Install de Open JDK 8
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get install openjdk-8-jdk
# Install de SBT
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823
sudo apt-get update
sudo apt-get install sbt
```


#### Attention :
1. Les laptop utiliseront Linux comme OS
2. Ne comptez pas trop sur la connection Internet le jour de l'atelier : pensez à inclure tout ce qui permettrait de dérouler les TP en mode offline.

