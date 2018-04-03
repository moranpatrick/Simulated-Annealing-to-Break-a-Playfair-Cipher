# Simulated Annealing to Break a Playfair Cipher
Fourth Year Artificial Intelligence Project

><b>Course:</b> BSc (hons) in Software Development, Year 4       
><b>Module:</b> Artificial Intelligence  
><b>Student:</b> Patrick Moran    
><b>Stident Id:</b> g00179039    
><b>Lecturer:</b> Dr John Healy    

## [Contents](#contents)
* [Introduction](#intro)
* [Playfair Cipher](#play)
* [Simulated Annealing](#sa)
* [References](#references)

# Introduction<a name = "intro"></a>
This repository contains my solution to Fourth Year, Artificial Intelligence project.
## Project Requirements
<img src="images/req.png">

[Top](#contents) 

# Playfair Cipher<a name = "play"></a>
<img src="images/playfair.png">

The Playfair Cipher is a manual symmetric encryption technique invented by Charles Wheatstone in 1854. It was the first Cipher in history to encrypt pairs of letters. The Playfair Cipher uses digraphs to encrypt and decrypt from a 5x5 matrix constructed from a sequence key of 25 unique letters. Because of its simplicity, needing only a pencil and paper, it was used by the British Forces in World War I and II. It was widely used to protect sensitive but non-critical messages during combat. 

[Top](#contents) 

# The Simulated Annealing Algorithm<a name="sa"></a>
There are many optimization Algorithms but for this project will be using the Simulated Annealing Algorithm. This Algorithm is a method for finding a good solution to an optimization problem. 

Simulated annealing is an excellent approach for breaking a cipher using a randomly
generated key. Simulated Annealing uses randomization to avoid heuristic plateaux and attempt to find a global maxima solution. The following algorithm is what will be used in this project to break a Playfair Cipher.

### The Algorithm
<img src="images/simulated.png">

[Top](#contents) 

# References<a name = "references"></a>
https://en.wikipedia.org/wiki/Playfair_cipher  
http://rosettacode.org/wiki/Playfair_cipher#Java  


[Top](#contents) 
