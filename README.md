# Présentation

Dans le cadre du module de Programmation Mobile, vous trouverez dans ce repository un exemple d'application codé en Java sous Android Studio.

Le but de cette application consiste à afficher la liste des 100 cryptomonnaies les plus importantes du marché actuel.
Je me suis servis ce cette API https://api.coinlore.net/api/tickers/?start=0&limit=99 que j'ai personnalisé à 100 car il existe des milliers et des milliers de cryptomonnaies.

L'ensemble des données sont affichés dans un RecyclerView faisant appel à un RestApi (ou Appel Serveur), permettant de display les données de chaque Cryptomonnaies.

On y retouve donc differentes informations dans la liste en prévisualisation comme :

                                       -son Rang d'importance
                                       -son nom complet
                                       -son prix en USD ($)
                                       -son nom sur le marché
                                     
![alt text](https://github.com/Alloms/ProgrammationMobile/blob/master/README/ListeCrypto.png)
                                       
Ensuite, en cliquant sur un élément de la liste on y retrouve les mêmes informations et plus comme:

                                       -son marketcap
                                       -son prix en btc (bitcoin)
                                       -son taux de variation d'échange sur 1h,24h et 7j
                                       
![alt text](https://github.com/Alloms/ProgrammationMobile/blob/master/README/Informations.png)
                                       
Une amélioration sera faite avec l'intégration du logo de chaque cryptomonnaies afin de mieux les reconnaître. Ainsi que l'integration d'un graphique pour 
les taux de variations d'échange.

# Prérequis

- Installation Android Studio

# Descriptions de l'application

-L'application a été developpé sous une Architecture dite MVC (Model View Controller).

-L'application possède 2 écrans (Classement Top 100 des Cryptomonnaies et leurs informations).

-L'application possède également une fonction de Memory Cache.

# Icône de l'application

Cette application s'appelle CryptoMarket

L'application possède une icône à l'image du Bitcoin qui est la cryptomonnaie la plus connue du marché : 

![alt text](https://github.com/Alloms/ProgrammationMobile/blob/master/README/Appli.png)


# Auteur

Créateur de l'application @Alloms
