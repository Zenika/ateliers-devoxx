## Prérequis pour dérouler les exercices

```bash
# Install node
wget  https://nodejs.org/dist/v5.10.1/node-v5.10.1-linux-x64.tar.xz
tar xf node-v5.10.1-linux-x64.tar.xz
sudo mkdir /usr/share/node
sudo chown zenika:zenika /usr/share/node/
mv node-v5.10.1-linux-x64 /usr/share/node/
sudo ln -s /usr/share/node/node-v5.10.1-linux-x64/bin/node /usr/bin/node
sudo ln -s /usr/share/node/node-v5.10.1-linux-x64/bin/npm /usr/bin/npm

# Install VS code

# Install angular2-webpack-starter
mkdir ~/frontend
cd ~/frontend
git clone https://github.com/ggaulard/angular2-webpack-starter
cd angular2-webpack-starter
npm Install

# Install angular-cli
cd ..
git clone https://github.com/angular/angular-cli
cd angular-cli
npm install -g angular-cli
```
