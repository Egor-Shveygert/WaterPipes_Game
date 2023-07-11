WaterPipes

Cielom projektu je naprogramovať hru ako oknovú aplikáciu v jazyku Java s pomocou knižníc AWT a Swing. Používateľské rozhranie aplikácie má pozostávať z:

* canvasu (alebo JPanel) - ktorý bude tvoriť hernú plochu
* bočné menu (jeho umiestnenie si zvoľte sami, teda môže byť aj hore, alebo dole)
## Základná pozícia
Po spustení aplikácie vygenerujte pomocou algoritmu [náhodného prehľadávania do hĺbky](https://www.baeldung.com/cs/maze-generation#dfs-maze) cestu z trubiek, ktoré pôjdu od štartu po cieľ a vykreslite ju na canvas.
Hra bude začínať na hracej ploche veľkosti 8x8 polí, a bude možné jej veľkosť meniť (minimálne 3 veľkosti).

Cieľom hry je prepojiť štart a cieľ pomocou správneho otočenia trubiek. Ak sa nám podarí cestu správne otočiť, po jej skontrolovaní chceme prejsť do ďalšieho levelu (nanovo vygenerovať hraciu plochu).
Štart a cieľ hry sa náhodne generuje, vždy na protiľahlej strane hernej plochy (Ak je štart vľavo, cieľ musí byť vpravo, pozíciu treba vybrat náhodne). Trubky po vygenerovaní cesty musia byť náhodne otočené.

## Požiadavky

Hra má byť hrateľná pomocou myši, keď myšou prejdem ponad pole, dané pole sa musí zvýrazniť. Ak sa na danom poli nachádza trubka, pomocou kliknutia myši ju viem otočiť.
V menu sa má nachádzať:
* informácia o tom, v ktorom som leveli, a veľkosť plochy.
* komponent pomocou ktorého je možné zmeniť veľkosť hracej plochy (iba na hodnoty 8 a vyššie). Konkrétny komponent si môžete zvoliť sami, napríklad jeden z: Slider, JTextField, JComboBox. Pri zmene veľkosti vždy resetujem hru.
* tlačidlo, ktorým vieme hru zresetovať.
* tlačidlo, ktorým vieme skontrolovať správnosť našej cesty.

Stlačením klávesy R na klávesnici vieme tiež hru reštartovať, a pomocou klávesy ESC vypnúť, a pomocou tlačidla ENTER skontrolovať správnosť našej cesty.

Pri kontrole správnej cesty vyznačiť od štartu všetky správne otočené trubky až po prvú chybnú.

Pri zadaní môžte používať LOMBOK.


-----------------------------
WaterPipes

Task is to create the game as a window application in Java using the AWT and Swing libraries. The user interface of the application should consist of:

* canvas (or JPanel) - which will draw the game board
* side menu (you can choose the placement of this menu, it can also be on the top, or at the bottom of the window)
## Basic position
After starting the application, generate a path from the pipes that will go from the start to the destination using the algorithm [random search in depth](https://www.baeldung.com/cs/maze-generation#dfs-maze) and draw it on the canvas.
The game will start on a playing board of 8x8 squares, and it will be possible to change its size (minimum 3 sizes).

The goal of the game is to connect the start and the finish line by turning the pipes correctly. If we manage to turn the path correctly, after checking it we want to go to the next level (generate a new the playing board).
The positions of the start and the finish line are generated randomly, always at the opposite sides of the board (if the start is on the left, the finish must be on the right, the positions must be chosen randomly). After the path is generated the pipes must be rotated randomly.    

## Requirements

The game is supposed to be playable with the mouse, when I hover the mouse over a field, that field must be highlighted. If there is a pipe on the field, I can rotate it by clicking the mouse.
The menu should include:
* information about the level I am in and the size of the area.
* component with which it is possible to change the size of the playing area (only to values 8 and above). You can choose a specific component yourself, for example one of: Slider, JTextField, JComboBox. I always reset the game when resizing.
* the button with which we can reset the game.
* button with which we can check the correctness of our journey.

By pressing the R key on the keyboard, we can also restart the game, and use the ESC key to turn it off, and use the ENTER key to check the correctness of our path.

When checking the correct path, mark from the start all the correctly turned pipes up to the first faulty one.

You can use LOMBOK.
