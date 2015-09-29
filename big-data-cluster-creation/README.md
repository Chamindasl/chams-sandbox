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
Lets make 3 clones

1. Trun of original vm (will call it s1) if it is truning
2. Right click s1 in the vbox manger and select "Clone"
3. Name the clone as s2 and select "Reinitialise MAC... " and Click Clone
4. In the next step Select "Linked-clone" and proceed. 
5. Repeat the same for vms (s3 and s4)

Now we have 4 vms named s1, s2, s3, s4. We can run them parallely but we might see ip conflicts since we assigned ip manually in s1 and every vm has same ip 192.168.56.101


#### 7. Network between VMs.
Next thing we need to do is setup up communication between vms. Need to do following things for each vm

1. Change the ip
2. Change the hostname
3. Change the hosts

1. Change the ip
  In here I prefer using NetworkManager. Manually set the ip to 192.168.56.101
  
2. Change the hostname
```
  sudo vi /etc/hostname
  #give the name as s1.
```

3. Change the hosts
```sh
  sudo vi /etc/hosts
```
  append flowing

```
  127.0.1.1 		s1  

  192.168.56.101	s1
  192.168.56.102	s2
  192.168.56.103	s3
  192.168.56.104	s4
```

4. repeat that for s2, s3, and s4.
5. restart all vms.
6. from a vm we should be able to ping/ssh other by ip and name 

```
ping s1
ping s2
ping s3
ping s4
```

#### 7. passwordless ssh
passwordless ssh means, key based ssh. 
First will see a known example. When we need to push something to github without our password , what we will do is we paste our public key in github. 
So when we push, git sends our public key information to github and github can identify us using the key we have given to github early.  
Lets map the same scenario into our case. s1 needs to connect to s2 without s2's password, so what we need to is put s1's public key into s2's authorized_keys.
When s1 is sshing to s2, ssh will send s1's public key and s2 verifies that income key is from a known by comparing keys in s2's authorized_keys.

So will do followings for each vm 
1. Generate public/private keys again
```
ssh-keygen -t rsa
```
>override existing keys
> in here option `t` is type.

2. Copy/append public to each vms' authorized_keys. (I did it bit differently, I appended each vms (includig s1) public key  to s1's authorized_keys. now s1 has authorized_keys all pub keys. Then copy s1's authorized_keys to other vms)
```sh
cat ~/.ssh/authorized_keys | ssh s1 'cat >> .ssh/authorized_keys'
```

>Above command is bit confusing. what we are doing in here is we cat the current machines authorized_keys, then pipe it to ssh of s1 and cat again what we are piping in s1 and append it to s1s .ssh/authorized_keys
>Anyway at the end each vms authorized_keys should contain 4 vms pub keys

```
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDE3Sau+adWCWPwXW1dDhM1oSQptc6QebPTfpUw/tpU8qC2WEB5CfvxE8CxpcQ+Sf/PW0z1WNOZ1b+Q6DuS+jL/Ps0gF0tOOwSFrUAxYgy3c4KDVss48V8NSyQqe0AEjqhYFAvwYHnZ/ihqsXlQhoLEOKRUAI8Cd8rKgdHCbnUCDJrLvhTP1WD4iaIY5pS/a32o0A895hQnFFnUR/f2cSRs/GSgY5nHsTd5rbR5CWnzmCwdMoLsuNE+LPISsuM7t9rtDj9Bonar5BgJM/ztiv9FmKJwltlyTmX+icSmF+wNGH8n8kDKoDEm8rteVjS18H4Dk0CRdt2Uph3S0a5w7kVX chams@s1
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDkwaCqmZCqPFDJIp2EYq9H4HgfR9ESi6p6vgPIjTh+SHmp3q285KuLa3ZoInEroRT4NCnVuTCIxdvOFqnHcSoQGH7oG3RFnZLy9aCrKVP2l+fH013rhMkwY674oS7jk5LvHImgFpF3oidPwXXQ7jEIf1OZJ3Jlt8x034Pt36VdQIejFww9cL7vIa0oIurxNIA9QNfwNiA1ncsjZN/sXuLVry1wDHSoPe4MTeW0vG6IkUOneXk+t7dwjyapWkh/HMZ4kLSaYODO2/L82DYdxdNCDEWl2BcC9Kjk34xK7CzkawqCW1H2x5LN0eJRmprRcxS/FMIf09XyEe2jk0BWHd4H chams@s2
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDhQQIcEEqpj1am1cAvCGY4wvswNMiSBnWdiS7aritS6c8whhm2JmR02gfHBxJAAfK8Kyn+47XjoJOZaI2uhpLLhIc3YuSfY3Kbmh9lYNvigDVFmVQEIAMcFpczbdZ8laanxQ53zllJ72uESGdrIEmBiP3EqU2mBBJX3jty5l07dOJh+Pq9j2hXOxKeKzvFBSvBOcN1uHHAvqp1YNYNu5Yldud1R7RuEMX9qGgOGYfgp91VAaMwujTcjaVfbDYAqsnzDzWQzdR1BUY+BLLCtehtpT4KOGrdoy3jvrT+eq3+RdDKKe0IElLW/a1NP1PvG3Pe8wPX/Bxu1lB+mcCqQ1EV chams@s3
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDClKMmYtT73pdRaHrEumxLpTX1MY5BSs56aBKJ35hrcZ1Y4KrW6AwamQyO3FuzmOXA15b48M+eL2xnPYSO1NfOqPgvP4T6SngBKqFJBADPHOXIo0UkmH3o1JaGnzmd3S2Q+9xU9657php20KJk3KSMSPwWFH6e4+EieJ4mjsIHVaXKID86PgyxxQTRc8f6gBCDr09E/wj3hfpRA/+9rEDZibt+0uLzIDspRUeOGy1avKG2icHk/fFogpruyhkTDURNrEpu7BVpvlWl/P9C66d+YclIYbothikFVG23MCUwRBdt11ITclYtYnDBXuY650Vswe5tqPnn+cZittO0RKVh chams@s4
```

All set. you should be able to ssh each other. If not working restart and try. Still not working set permissions of .ssh and .ssh/authorized_keys
	