# Big Data Learning.
## Making a Cluster
Though I am going to use this cluster now for big data leaning, keeping in mind to use this in other purposes as well in the future I am trying to keep this generic as posssible. 

There are so many ways make cluster for testing purpose in localhost. In here I will use simple virutual box.

### Plan
1. Create LinuxMint VM
2. Network between Host and VM
3. Internet in VM
4. Setup SSH
5. Install JDK
6. Clone VM to create additional 3 VMs
7. Network between VMs
8. Passwordless ssh between VMs
9. Textmode boot for VMs


#### 1. Create LinuxMint VM
Not going to explain things in detail, Basically we need virtual box, vmware or similar. As I mentioned early I will use virtualbox.

1. Install viritual box
2. Download LinuxMint's Cinnamon No codecs from here http://www.linuxmint.com/download.php
   * LinuxMint is lightweight than ubuntu, 
   * No codecs is used because we are not going to play music or videos in this machine
3. Create a new VM with 20 GB hd and 2 GB RAM
4. Set the downloaded iso as Storage
5. Start VM and Install LinuxMin
6. If you get "Cinnamon running in software rendering mode" message after installation, Power off VM,  Enable 3D acceleration under Display section of virtual box and restart VM. 


#### 2. Network between Host and VM
1. Enable 1st Adapter as Host-Only Adapter from settings of VM
2. Set fixed ip for the VM manually. Eg 192.168.56.101. Do not go with DHCP.
3. Check the connectivity between host and vm. Both should be able to ping other.


#### 3. Internet in VM
1. Enable 2nd Adapter as NAT
2. Check internet in VM


#### 4. Set SSH
```sh
chams@c1 ~ $  ssh-keygen -t rsa
Generating public/private rsa key pair.
Enter file in which to save the key (/home/chams/.ssh/id_rsa): 
Created directory '/home/chams/.ssh'.
Enter passphrase (empty for no passphrase): 
Enter same passphrase again: 
Your identification has been saved in /home/chams/.ssh/id_rsa.
Your public key has been saved in /home/chams/.ssh/id_rsa.pub.
The key fingerprint is:
77:3e:09:bb:c7:57:4e:b9:e5:cc:25:89:bd:47:e1:0d chams@c1
The key's randomart image is:
+--[ RSA 2048]----+
|                 |
|                 |
|                 |
|              E. |
|        S o .o.o+|
|         . =..+oB|
|          ..+  @+|
|           .o.o.*|
|          .. . . |
+-----------------+
```

 
#### 5. Install JDK
1. Download JDK from Sun (Oracle)
2. Export env vars in .profile
``` sh
export JAVA_HOME=/home/chams/sw/jdk1.8.0_60
export PATH=$JAVA_HOME/bin:$PATH
```
3. Apply changes and Varify
```ssh
chams@c1 ~ $ source .profile 
chams@c1 ~ $ export | grep 'PATH\|JAVA' 
declare -x DEFAULTS_PATH="/usr/share/gconf/cinnamon.default.path"
declare -x JAVA_HOME="/home/chams/sw/jdk1.8.0_60"
declare -x MANDATORY_PATH="/usr/share/gconf/cinnamon.mandatory.path"
declare -x PATH="/home/chams/sw/jdk1.8.0_60/bin:/home/chams/sw/jdk1.8.0_60/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games"
declare -x WINDOWPATH="8"
chams@c1 ~ $ java -version
java version "1.8.0_60"
Java(TM) SE Runtime Environment (build 1.8.0_60-b27)
Java HotSpot(TM) 64-Bit Server VM (build 25.60-b23, mixed mode)
chams@c1 ~ $ 
```

#### 6. Clone VM to create additional 3 VMs
We came through a painful process. Still 3 more vms to setup. Instead of reiterate the process again and again we can clone the vm what we have setup.
Good thing with virtualbox cloning is they have nice cool feature called "Linked-clone", in where they create completely new vm but make a harddisk snapshot of original one.
Using this we no need to waste 80 gb (20 x 4) hd space for our 4 vms instead 20 gb for original + less than 1 gb other 3 vms.
Disadvantage of this method is cloned vms are not portable but its not an issue in our case. 