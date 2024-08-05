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

## Security techniques
## Analysis of provided security
## Technologies
## References
