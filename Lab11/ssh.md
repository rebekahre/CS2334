# Commands
Check for updates that are available for the operating system and installed packages.

```
    1  sudo apt update 
```

Check if Java is installed.
```
    2  java --version 
```

Install JRE.
```
    3  sudo apt install default-jre 
```

Check if Java Runtime Environment is insalled. 
```
    4  java --version 
```

Check if `git` is installed.
```
    5  git 
```

Install git.
```
    6  sudo apt install git 
```

Check version of your git installation.
```
    7  git --version 
```

Configure git.
```
    8  git config --global user.name "Your Name" 
```

```
    9  git config --global user.email "Your-Email-Name@ou.edu" 
```

Clone your repository from `GitHub`.
If you have two-factor authentication enabled on your account, you will need to create a [personal access token](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token) and enter it instead of your password.
```
   10  git clone "Lab11 GitHub URL"
```

Check if you see the new directory listed with your repo in it.
```
   11  ls 
```

Change directory to your project.
```
   12  cd lab11-github-username/ 
```

```
   13  ls 
```

```
   14  cd src 
```

```
   15  ls 
```

```
   16  cd html 
```

```
   17  ls 
```

Compile your Java program.
```
   18  javac MakePage.java 
```

We did not install JDK, and so, we cannot compile just yet. More packages to install.
```
   19  ls
```

Install the JDK.
```
   20  sudo apt install default-jdk 
```

Check version of your freshly installed JDK.
```
   21  javac --version 
```

```
   22  ls 
```

Complie your Java program.
```
   23  javac MakePage.java 
```

```
   24  ls 
```

Try to run your java program.
```
   25  java MakePage 
```

You may have to set $JAVA_HOME environment variable.
```
   26  echo $JAVA_HOME 
```

Check if you have `nano` installed.
```
   27  nano 
```

Install `nano`.
```
   28  sudo apt install nano 
```

```
   29  echo $JAVA_HOME 
```

You have to make changes to your global environment in the `super user` mode.
```
   30  sudo nano /etc/environment 
```
Set `JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/`

```
   31  which java 
```

```
   32  ls /usr/lib/jvm/java-11-openjdk-amd64/ 
```

```
   33  echo $JAVA_HOME 
```


```
   34  source /etc/environment 
```

```
   35  java MakePage 
```

```
   36  echo $JAVA_HOME 
```


```
   37  /usr/bin/java MakePage 
```

```
   38  ls 
```

```
   39  javac MakePage.java 
```

```
   40  java MakePage 
```

```
   41  sudo update-alternatives --config java 
```


```
   42  cd .. 
```

```
   43  ls 
```
You should be in `src` directory now.

```
   44  java html.MakeJava 
```

```
   45  java html.MakePage 
```

```
   46  ls 
```

Now, we have to install `nginx`, the webserver application we will use.
```
   47  sudo apt install nginx 
```

In the list of VM instances on the GCP website (the same page where you clicked the link to connect via SSH), there is a column labeled "External IP."
Copy the external IP address of your instance, and try to connect to it in a browser.
The URL of your server is "http://" + ip-address.
For instance, if the IP address is 1.2.3.4, the URL is "http://1.2.3.4".
(You can click the IP address on the GCP website to connect, but you'll need to change the prefix from "https" to "http".)

It may not be accessible, but you can try this command that runs on your server (VM)
```
   48  curl localhost 
```
So, your web server application (nginx) is available from your `localhost`, but not from the `www`. We can fix it by allowing access to your server from the `www`.


```
   49  sudo apt install ufw 
```

```
   50  sudo ufw app list 
```

Allow `nginx` to be visible from `www`.
```
   51  sudo ufw allow 'Nginx HTTP' 
```

```
   52  sudo ufw app list 
```

```
   53  sudo ufw status 
```

```
   54  systemctl status nginx 
```
You have started your `nginx`, hit `^z`, and then type:

```
   55  bg 
```
This have put your web server process into the background.

Try again to access your server from your browser.
(Make sure the URL prefix is "http" and not "https".)

Now, go to the directory that will hold your web pages. `pushd` is more versatile than `cd`. You can `pushd` to the directory of your choice, and `popd` will take you back to the previous directory.
```
   56  pushd /var/www/html 
```

```
   57  ls 
```

Create a directory for your pages.
```
   58  mkdir colors 
```

You may have to be a `super users` to do it.
```
   59  sudo mkdir colors 
```

Change the ownership of the directory and its content.
```
   60  sudo chown -R $USER:$USER /var/www/html/colors 
```

Change the access permissions to the above mentioned directory.
```
   61  sudo chmod -R 755 /var/www/html/colors 
```

Check if you can access the directory.
```
   62  curl localhost/colors 
```

```
   63  curl localhost/ 
```

Now, we have to modify the configuration of your `nginx` installation, to allow for directory listing of `colors`.
```
   64  pushd /etc/nginx/sites-enabled/ 
```

```
   65  ls 
```

```
   66  sudo nano default 
```

You have to change the default settings for your web server.
You have to allow for the directory content to be listed. 
Add the following below the entry `location / { try_files $uri $uri/ =404; }`:
```
        location /colors {
                autoindex on;
        }
```

```
   67  sudo nginx -t 
```

After the config file modification, we have to restart our `nginx` installation.
```
   68  sudo systemctl restart nginx 
```

Verify that you can see the `colors` directory in your browser by opening the URL "http://" + external-ip + "/colors".

```
   69  popd 
```

```
   70  ls 
```

```
   71  cd colors 
```

```
   72  pwd 
```

```
   73  popd 
```

```
   74  ls 
```

```
   75  cd html 
```

```
   76  ls 
```

Open the source file for the program in nano:
```
   77  nano MakePage.java 
```

Make the following changes:

1. Switch the value of the constant `DIRECTORY` from `"./colors/"` to `"/var/www/html/colors/"`.
2. In the `myPage` method, replace the string `"Hello"` with your full name.
3. Use the method `System.nanoTime()` to calculate the time from the start of the `myPage` method to the line that appends the string `"<p> You could provide your text here. </p>\n"`. 
Replace `"You could provide your text here."` with the time, using the format `"Time: nano-seconds"`.

Save (write out) and exit.

```
   78  cd .. 
```

```
   79  pwd 
```

Run your Java program.
```
   80  java html.MakePage 
```

Refresh your browser.

