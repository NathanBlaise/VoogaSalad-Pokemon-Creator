for the game's save and load, we use serialization of the workspace for now.

At the beginning of a new edit, the authoring environment create a new workspace object and set all the values in the workspace to be default values, where default values can be written in a property file for now.

When the user wants to save the editted game, the authoring environment saves workspace the as a xml using serailization. The same operation is done when the user wants to save the game after he played.

When the user wants to load the editted workspace and keep editing or keep playing, a module (APIs) in data should be responsible for loading the object from xml file and pass the object to other modules whichever needs it.

The structure of workspace can be seen in default_workspace.java