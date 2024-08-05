# HashVault

"HashVault" is an all-in-one locally installed password manager that keeps 
users' passwords and other sensitive information and credentials safe for all at 
the convenience of a single master key.

## Table of Contents
1. [Introduction](#introduction)
2. [Comparison of approaches](#comparison-of-approaches)
3. [Selection of cryptographic tools](#selection-of-cryptographic-tools)
4. [Security techniques](#security-techniques)
5. [Analysis of provided security](#analysis-of-provided-security)
6. [Technologies](#technologies)
7. [References](#references)


## Introduction

In the current world, passwords continue to be the primary way of user authentication in local applications, web applications as well as in computing devices. With the increase in popularity of web-based services, the number of accounts that a user has to register expands gradually. With that arises the vital problem. Because users will not pay much attention to the password strength or users, re-use the same password several times when creating new accounts. Although it reduces the work that had to be done to remember the passwords, it puts the user at a significant security risk. Because if an attacker gets the credentials of a single account attacker will be able to get access to every other account that uses the same password. Research conducted by Verizon Data Breach Investigations shows that weak passwords cause 81% of all data breaches. (Most Security Breaches are due to Weak Passwords | Professional Security, 2020). 

A password manager can help to fix this issue. A password manager is a software that allows the user to store all the passwords in a safe location in a secure format. It requires the user to remember a strong, single password that protects all other passwords. This will give the user the benefit of having a different password for every account while remembering only a single password (Gasti and Rasmussen, 2012).


## Comparison of approaches

There are different ways to categorize password managers. When they are categorized according to the way they are installed or the way they store data, there are 3 prominent types.

- Web-based password managers
- Locally installed password managers
- Token-based password managers

Online based password managers store data in a server after encrypting. The main advantage of an online password manager is that data can be accessed via any device that a user has. Most of the commercially available password managers used this architecture. The main issue in this approach is that the server that stores data are vulnerable to attacks. But with a secure master key and strong encryption algorithm, these risks can be minimized. Locally installed password managers store data in the installed device itself. If the device is not connected to the internet there is a very low chance of intruders getting the data. The main drawback of an offline password manager is that data can be accessed only through the device where the password manager has been installed.

Token-based password managers use a hardware device similar to a USB drive to contain the master key. These password managers do not store passwords, instead, they generate tokens when the user needs to access an account. The biggest advantage of such managers is that they do not contain a collection of passwords (CyberNews, 2020). After considering the time constraint as well as specified requirements, it was concluded that an offline or locally installed password manager will be the best approach for the developed solution. 

## Selection of cryptographic tools
In a typical password manager, there are two main things to be protected when saving data. The first component is the master password, and the second component is the data entered by the user, such as passwords and notes. To secure the master password, usually hash function is used. A hash function is used because if an encryption algorithm is used then the program has to store the key for decrypting that encrypted master password. Which will compromise the security of the program. And it will open another problem of saving and keeping the key used for encryption or the master key secure. A hash function solves all of these issues presented by encrypting a master password. A hash function is a function that map arbitrary length inputs to fixed-size output. These are also known as message digest (Al-kuwari, Davenport and Bradford, 2011). 

The main feature of a hash function in storing a password is that it is irreversible (one-way function). That means it is impossible to get the original input using the hash value, given that a secure hashing function is being used. Another feature of a hashing function is that it produces the same digest for the same input every time.  When storing the master password, a hashing algorithm can be used to hash the master key. And the resulting hash value can be stored. When the program needs to authenticate a password, it can compare the hash value of provided password with the hash value of the original password stored and verify the provided password. With the research that was conducted, it was identified that the SHA3-256 is the best hashing algorithm for the implemented solution. It is a 256-bit hashing algorithm that greatly reduces the risk of hash collisions.

To securely store the entered data in the password manager such as passwords and other notes, an encryption algorithm had to be used. Because Symmetric-key encryption is much more effective and faster than Asymmetric key encryption, it was identified that a symmetric key encryption algorithm had to be used (Abdullah, 2017). Symmetric key encryption algorithms use the same key to both encrypt and decrypt data.

There are two major categories of symmetric ciphers. They are Block ciphers and Stream ciphers. Block ciphers encrypt the plaintext in fixed-size blocks such as 64-bit and 128-bit. Stream ciphers encrypt data in real-time bit by bit. Because the developed application does not produce a stream of data it was identified that a block cipher can be used to encrypt the program data. DES, AES, IDEA and Blowfish are some examples of symmetric block ciphers. From the research conducted it was identified that AES is the best algorithm to encrypt the program data because it is much more secure than DES, and DES was designed for hardware implementation is not efficient in software. 

Advanced security standard (AES) is one of the most secure and widely used symmetric block ciphers in the world. AES can have key sizes of 128, 192 and 256 bits. And there is no evidence of cracking the AES algorithm to date (Abdullah, 2017). There are six modes of operations in the AES algorithm. Namely,

- Electronic Code Book (ECB) 
- Cipher Block Chaining (CBC)
- Cipher FeedBack (CFB)
- Output FeedBack (OFB )
- Counter (CTR)
- Galois Counter Mode (GCM)

The use of the above operation modes will increase the effectiveness of the encryption algorithm. From the research conducted, it was identified that the CBC mode is suitable for the implemented solution. In the CBC mode of operation, each plaintext block is XOR'ed with the preceding block of ciphertext before being encrypted. It prevents identical plaintext blocks from being encrypted into identical ciphertext blocks. To encrypt the initial block of plaintext it uses an Initialisation Vector (IV).

## Security techniques
The program provides security for the master password and secondary passwords separately. Initially, when setting the master password, the program will validate the user-entered password. The master password selected by the user should contain at least one uppercase letter, one lowercase letter, one special character, and should be longer than eight characters. This will ensure the strength of the master password. When the user sets the master password, it will be hashed along with a "salt", using the SHA3-256 algorithm. The resulting hash value is Base64 encoded and saved in the "Login Data" file. When the user enters the master password to log in to the system, the program will Base64 decode the stored password and get the hash value and compare it with the entered password's hash value. And authenticate the user. If the user enters three wrong master passwords in a row, the user has correctly filled the reCAPTCHA. It contains a simple arithmetic question that helps to protect the program from spamming and abuse.

To protect the secondary passwords, they are AES-256 encrypted before saving them into the "Program Data" file. When the user exits the program entered passwords, usernames and descriptions are AES-256 encrypted in CBC mode as an object. The program will generate the IV for encryption and the master key along with a "salt" is used to generate the key for encryption. The encrypted object is then serialized and saved in the "Program Data" file.  When retrieving the data from the file, the saved serialized object is deserialized and decrypted using the key generated from the master password and the "salt".

## Analysis of provided security

The master password set by the user is hashed using SHA3-256 to protect it. A hash function is an easy to compute one-way function which converts an arbitrary-length message to a fixed-length hash value. And making finding a hash collision computationally infeasible. SHA is more secure than MD4 or MD5 hashing algorithms. Because SHA-256 has a 256bit message digest size, it provides a security level against collision search attacks and brute-force attacks (Gilbert and Handschuh, 2004). Because of that, it will be nearly impossible to get the correct value of the master key from the saved data file. This will prevent unauthorised access to the program because, without the correct master password, intruders cannot access the content of the program.

The program data (secondary passwords and descriptions) are encrypted with AES 256.  AES has been accepted as the de facto standard in security-related applications. Although AES is claimed to be theoretically broken using several models and techniques, currently, there are no records of breaking AES practically. This is due to the high complexities of the attacks. Because of that AES is still practically secure (Isa et al., 2011). The key used for the encryption is also secure because it is derived from the master password. Because the program does not store the master password (for the login verification hash value of the master password is stored), the user is the only one who knows the value of it. Even though an attacker gets access to the "Program Data" file, the attacker will not be able to get any useful or meaningful data from it without knowing the master password, the “salt” used, and the key generation algorithm.

## Technologies

- Java
- JavaFx
- CSS

[![Built with](https://skillicons.dev/icons?i=java,css,figma)](/)

## References
- Abdullah, A. (2017). Advanced Encryption Standard (AES) Algorithm to Encrypt and Decrypt Data.
Available from  https://www.researchgate.net/publication/317615794  [Accessed 23 December 2021].

- Al-kuwari, S., Davenport, J.H. and Bradford, R.J. (2011). Cryptographic Hash Functions: Recent Design Trends and Security Notions. Available from http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.400.6901&rep=rep1&type=pdf.

- CyberNews. (2020). How password managers work. Available from https://www.youtube.com/watch?v=CtOh1-TUabU [Accessed 23 December 2021].

- Gasti, P. and Rasmussen, K.B. (2012). On the Security of Password Manager Database Formats. In: Foresti, S. Yung, M. and Martinelli, F. (eds.). Computer Security – ESORICS 2012. Lecture Notes in Computer Science. Berlin, Heidelberg: Springer Berlin Heidelberg, 770–787. Available from https://doi.org/10.1007/978-3-642-33167-1_44 [Accessed 23 December 2021].

- Gilbert, H. and Handschuh, H. (2004). Security Analysis of SHA-256 and Sisters. In: Matsui, M. and Zuccherato, R.J. (eds.). Selected Areas in Cryptography. Lecture Notes in Computer Science. Berlin, Heidelberg: Springer Berlin Heidelberg, 175–193. Available from https://doi.org/10.1007/978-3-540-24654-1_13 [Accessed 27 December 2021].

- Isa, H. et al. (2011). AES: Current security and efficiency analysis of its alternatives. 2011 7th International Conference on Information Assurance and Security (IAS). December 2011. 267–274. Available from https://doi.org/10.1109/ISIAS.2011.6122831 [Accessed 23 December 2021].

- Most Security Breaches are due to Weak Passwords | Professional Security. (2020). professionalsecurity. Available from https://www.professionalsecurity.co.uk/news/announcement/most-security-breaches-are-caused-by-weak-passwords/ [Accessed 23 December 2021].

