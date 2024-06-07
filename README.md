# Athenauem version 2

## Description
A revision of original Athenauem project [which can be found here](https://github.com/ColinLouis95/Athenauem). This updated version's design includes a simpler UI, MySQL database for storing user data, and no CLI interface.  

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Contributing](#contributing)
- [License](#license)
- [Contact Information](#contact-information)
- [Acknowledgements](#acknowledgements)






## Installation 

1. Download the project files from box that says "code"
2. Navigate to the downloads folder on your computer
3. Move the project to an index of your choosing, such as ~/User/index of choice/project
4. In your favorite IDE, open the project files by navigating to where you placed the project files and open.
5. Make sure to have MySQL installed on your computer and is running.
6. In your IDE, run Athenauem.java
### Note! if this crashes your computer, you will have to create the tables in MySQL (I am working on adding those files to the project :)

## Usage
This application came to be due to an final project given bak in college. Orginally it was just suppposed to be a CLI interface where in memory it would store a username and password, which can be displayed or edited or deleted. It would then save 
to a .txt file which could be viewed if so desired. Over the years I have refined this project, with the orignal Athenauem repository (linked above) having updates including a GUI interface, encryption/decryption for passwords, the addition for what the information was for (website).
This version is aimed to have a simpler UI, better encryption/decryption methods, a database in which all information is stored and retrieved, and so much more.

## Features
![image](https://github.com/ColinLouis95/AthenauemV2/assets/57474778/2f837c89-4e0e-46cc-9291-5a54898bd0eb)


This is the current, re-designed home menu for Athenauem. I made it to be a smaller window since I wanted this project to have an app-like feel to it. From this window, the user will be able to either view the contents of their "vault", or add a new entry to their vault.



![image](https://github.com/ColinLouis95/AthenauemV2/assets/57474778/39b72d54-8dce-46fe-b540-dc6131237539)


This is what it currently will look like when the user wants to add a new entry into their vault, in which the user **MUST** enter in entries for all three fields, otherwise it will not save. Once the user clicks "Add", it will let the user know the entry was saved and that they can either add a new entry in or return to main menu.



![image](https://github.com/ColinLouis95/AthenauemV2/assets/57474778/7a5a0491-4360-4d19-ac37-1f8b71d05340)


This is what it currently looks like when the user will click on "display vault". Currently there is a known issue where it displays a fourth column, which in future versions will be for an added info section for the password. **NOTE** this will only work if the MySQL database is up and running, with the tables formatted correctly. This will be added into the respository in a future update.  



## Contributing
Any contributions are welcomed! Whether it be creating issues to pull requests, I am happy for people to want to work on my project. I do not intend for this to ever be used officialy, rather a pet project showcasing Java skills! For contact about making a contribution, please refer to the [contact information](#contact-information) below!


## License 
MIT License

Copyright (c) 2024 Colin Kugler

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


## Contact Information
For any contributions to be made to Athenauem, please send them to kuglercolin4@gmail.com with the following message...
### Subject - (Insert contribution type) for AthenauemV2
#### Body - "Hello, my name is (insert github username) and i would like to make a (contribution type) to the AthenauemV2 project." Then tell me about the contents of the contribution and I will add you onto to the project.

## Acknowledgements
So far the only acknowledgements to be mentioned is professor Tracy Dobbs of ACC in Littleton, CO and ChatGPT.
