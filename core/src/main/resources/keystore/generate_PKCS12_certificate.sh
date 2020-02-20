#!/bin/bash
LG='\033[1;32m' # LIGHT GREEN
LY='\033[1;33m' # LIGHT YELLOW
NC='\033[0m'    # NO COLOR

# Remove old certificate
file="kindergartensystem.p12"
if [ -f $file ] ; then
	printf "${LY}Removing old certificate file - ${NC}$file\n\n"
	rm $file
fi

# Generate random password
random_password=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 64 | head -n 1)
printf "Your random password is:\n${LG}$random_password${NC}\n\n"
printf "Make sure to copy it - since you will have to add it to configuration.\n\n"

# Generate PKCS12 certificate
keytool -genkeypair -noprompt \
-alias kindergartensystem \
-dname "CN=localhost, OU=KindergartenSystem, C=PL" \
-keyalg RSA \
-keysize 2048 \
-storetype PKCS12 \
-keystore kindergartensystem.p12 \
-storepass $random_password \
-validity 90

# End script
printf "${LG}Success!${NC}\nScript execution finished. Press any key to continue.\n"
read -p ""
