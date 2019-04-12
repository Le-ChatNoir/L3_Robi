# L3_Robi
Java project about the gestion of Morph objects (Robis) in a World environnement. Setting up Macros and Robis inside Robis.
Most recent version is Exercice10 in the RobiMultiEx9 project.

Following: Documentation in french:


Robi & Worlds
Présentation du projet

	Durant ces trois derniers jours, nous avons réalisé en pair-programming un projet développé en Java.  Ce projet a été découpé en deux parties. Premièrement, les sept premiers exercices qui ont été programmés selon une logique plus "itérative", puis, une refonte complète du code a été revue afin de l'orienter plus vers l'objet. Le fichier le plus récent est la classe Exercice10 contenue dans le projet RobiMultiEx9.


	Après avoir exploré le code fourni comme point de départ dans les exercices 1 et 2, nous avons, dans l’exercice 3 créé quatre classes, héritées d’une classe abstraite Command, correspondant aux flèches directionnelles (Up, Right, Down, Left). Dans l’exercice 4, cette implémentation permettait l’instanciation de ces classes par leur lettre clé que l’utilisateur entre devait entrer pour contrôler le robot. Dans l’exercice 5, nous avons modifié les classes de contrôle afin d’y incorporer un paramètre définissant là distance parcourue par le Robi à chaque appel. En gardant la même structure, nous avons implémenté le système de macro à l’aide de sa classe homonyme. Cet objet possède un champ nom ainsi qu’une ArrayList de tableau de String destiné à accueillir les commandes. Lorsque, dans l’exercice 7, nous avons dû faire en sorte que plusieurs Robi puissent coexister au sein de la fenêtre graphique, nous avons ajouté, à la classe Macro, un champ contenant le Robi auquel elle était associée.
	Cependant, l’ajout de ces fonctionnalités couplée à la structure de notre code transformait peu à peu notre programme en bloatware difficilement compréhensible. Après avoir laborieusement mis en places les fonctions demandées dans l’exercice 8, nous avons pris la décision de remodeler la structure de notre programme de A à Z.

Les problèmes de la V1

Suivant les conseils de nos professeurs, nous avons entrepris de reprendre notre code afin d’en récapituler le fonctionnement et d’en imaginer une version plus objet. La liste qui suit décrit exhaustivement le fonctionnement de l’exercice 8, les problèmes qui en découlent et les améliorations à lui apporter.

Lignes
Explications/Problèmes
15-17
Liste d'élements:
    • Classes "commande" indexée selon une chaîne de caractères
        ◦ Permet de tester l'existance de la commande avec containKey()
        ◦ Permet d'éxecuter facilement les commandes grâce aux clés
    • Macro
        ◦ Liste contienant toutes les macros
        ◦ Chaque macro est liée a un objet "morph"
    • Morphe, indexés selon leur nom
        ◦ Permet la création et le nommage des morphs
        ◦ Les morphs sont ensuite appelés grâce à leur nom

=> Listes seulement accessibles dans le main
=>Solution trouvée : Transormer ces listes en classes
24-28
5 classes commande (Up , Down, Left, Right et Color)
    • Fonctionne lorsque l'on a un seul morph
    • Dès qu'un morph est créé, nous devons alors ajouter 5 classes à la liste
35-40
La lecture et l'interprétation des commandes clavier sont facilitées grâce à la Map commandByKey et readCmdAndArgs
=> Le mot clé est toujours le token fin[0]
43-58
Création de macro
s = Nom de la macro
token[1] = nom du robot
52-56
 => On ajoute une a une les commandes a la liste cmds de notre instance de Macro
62-69
Appel macro
    • On parcourt la liste des macros existantes jusqu'à trouver celle qui correspond 
      A améliorer
      En liant la macro aux robots plutôt que les robots aux macros, on pourrait ne parcourir que les macros du morps appelé dans la commande et ainsi réduire le nombre d'itérations
    • Similarité dans la grammaire d'appel des commandes lignes 39-66
      A améliorer
      On pourrait peut-être créer une méthode d'appel commune
71-99
Création et suppression des (Robi, Macro)
    • Les cinq classes sont instanciées et ajoutées à la liste lors de la création
    • Lors de la suppression d'un robi, on parcours toute la liste des commandes pour désinstancier les commandes
    • La même procédure est utilisée pour désinstancer les macros
      =>Beaucoup de calculs sont nécessaires


Proposition : Nous pouvons alors regrouper toutes les classes « Command » en une seule classe nommée CommandCentral car elles sont communes à tous les Robis Cela permet alors d’éviter de gérer une Map des commandes.


La version finale

La dernière version de notre programme est composée de 4 classes :
    • CommandCentral qui traite une partie des instructions envoyées au(x) robot(s)
    • Macro qui enregistre une liste de commande
    • Robi qui est une représentation « physique » des robots servant de passerelles entre l’aspect graphique qu’est la classe Morph et l’interface utilisateur qu’est le Main
    • Exercice10  qui fait office de Main et sert à récupérer et interpréter les commandes de l’utilisateur


	a) La classe CommandCentral
Cette classe contient une méthode run chargée d’interpréter le premier token (fin[0]) reçu en paramètre. Elle appelle ensuite la méthode correspondante dont les paramètres sont contenus dans le reste du tableau fin. On a choisi de placer le switch/case dans cette méthode afin de désengorger la classe Exercice10 et rendre cette dernière plus compréhensible.
Les cinq autres méthodes correspondent aux anciennes classes héritées de Command et dictent le comportement du Robi passé en paramètre.
A la fin d’une commande de contrôle de Robi son champ lastCommand est actualisé. Ainsi, lors de l’exécution de macros, ce champ est écrasé à chaque commande, ce qui permet de ne garder, une fois la macro terminée, que le retour de la dernière commande exécutée.

	b) La classe Macro
Cette classe est plutôt simple. Elle contient  : 
    • Un nom, nécessaire à l’appel de la macro par l’utilisateur
    • Un ArrayList<String[]> contenant les commandes à exécuter précédemment formatées par la fonction readCmdAndArgs().

	c) La classe Robi
La classe Robi contient le nom du robot, son Morph, la liste des macro qui lui sont associées, la dernière commande qui lui a été appliqué sous la forme d’une chaîne de caractère et un champ curColor contenant la couleur actuelle du Morph.
Cette classe comporte deux constructeurs, selon si le nouveau Robi est contenu dans un autre Robi ou non, des accesseurs et des fonctions de gestion des macros.

	d) La classe Exercice10
Les Robi sont instanciés dans la HashMap robiByName où ils sont classés selon leur nom. On entre ensuite dans une boucle dans laquelle l’utilisateur pourra entrer ses commandes au clavier. Celles-ci, découpées en tokens grâce à la fonction readCmdAndArgs(), sont traitées selon leur premier token :
    • Si celui ci contient le nom d’un Robi, c’est que la commande est soit un appel de macro, soit une commande directe, que l’on exécutera alors grâce à la méthode run de la classe CommandCentral. Lors d’un lancement de macro, on commence par vérifier son existence au sein de la liste de macro du Robi appelé dans le premier token. Si la macro est trouvée, on parcourt sa liste de commandes cmds que l’on exécute via la méthode run.
    • Si le premier token est ‘(’, c’est que l’utilisateur souhaite créer une macro. On utilise alors la méthode addMacro() de la classe du Robi instancié sous le nom récupéré dans fin[1].
    • Dans le cas où le premier token est new, c’est qu’on souhaite instancier un nouveau Robi. On teste la longueur de la commande pour savoir si ce dernier doit faire partie d’un autre Robi (comme demandé dans l’exercice 9). Si c’est le cas, le constructeur est légèrement différent et l’utilisation de la méthode addSubmorph() est requise.
    • Si le premier token contient le mot clef delete l’utilisateur souhaite supprimer un Robi. On supprime alors ses subMorphs ainsi que les sous-Robi encore présent dans l’ArrayList robiByName.


Vers une meilleur mise en œuvre

	a) CommandCentral.run()
L’utilisation d’un switch/case telle que nous l’avons faite nécessite beaucoup d’opérations à chaque appel. Cette implémentation ne permet une évolution flexible du code. Car l’ajout d’une nouvelle commande nécessitera la création d’une nouvelle méthode et du cas correspondant dans le switch.

	b) La suppression d’un Robi
Le code le plus lourd présent dans le fichier Exercice10 est de loin le code de suppression d’un Robi. En effet, celui-ci n’est pas géré via une méthode de la classe Robi, mais directement en code brut. De plus, afin de pouvoir supprimer les sous-Robis du Robi sélectionné, une boucle se fait sur sa liste de Submorphs. L’implémentation actuelle est donc fonctionnelle, mais son traitement ne s’effectue que sur une seule « couche » de sous-Robis. Il nous faudrait donc, pour pouvoir aller plus loin, implémenter une méthode récursive dans la classe Robi.
