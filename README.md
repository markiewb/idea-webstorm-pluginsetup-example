# Example: How to setup a WebStorm-Plugin

This is an example how to setup a plugin for WebStorm or IntelliJ IDEA Ultimate. 

The plugin only purpose is to add the action "TSLint Format" to the code menu of the main menu, which invokes `com.intellij.lang.javascript.linter.tslint.fix.TsLintFileFixAction`. 

The plugin wants to access code from the TSLint plugin (provided only by the commercial JetBrains products above) 
and thus there was some more setup to do.


## Setup your own plugin

1. Create a new plugin via `File->New...-> New Project...-> IntelliJ Platform Plugin`
2. Setup your target platform/SDK to Webstorm
3. Add plugin-dependencies, you like to link to, to the SDK 
    * The dependencies are located in the plugin-folder of the target-platform
    * Do not add them as separate dependency to the plugin module, but to the SDK - [see forum](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206762465-How-do-I-make-use-of-the-javascript-psi-)
    * ![SDK](https://raw.github.com/markiewb/idea-webstorm-pluginsetup-example/master/doc/SDK.png)
4. Add dependencies to the plugin.xml
    ```
    <!-- support also non-IDEA platform -->
    <depends>com.intellij.modules.lang</depends>
    <!-- activate the plugin, when JS-plugin is available and activated-->
    <depends>JavaScript</depends> 
    <!-- activate the plugin, when TSLint-plugin is available and activated-->
    <depends>tslint</depends>
    ```
5. Within your plugin, you can access the classes of the JavaScript- and TSLint plugin 
    * at compile time (provided by step 3)
    * at runtime (provided by step 3 and 4)
    