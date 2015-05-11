# Isomorphic-clojure-mspa
Multi-Single-Page Applications (MSPA) using Clojure - ClojureScript - Nashorn JVM.

*** Important: JAVA 1.8 Required

## Build the SPA modules
`./lein modules build`


## Start the server (and open the browser)
`./lein ring server-headless`

## Run server 
`./lein ring server`

NOTE: By default the app will work on port 2001.


## File Architecture

```
src/
  - server/
        core.clj
  - client/
  	- home/
  		- src/home/
		    core.cljs
		    state.cljs
		    action.cljs
		    - views/
		         about.cljs
		         main.cljs
		         ...
		project.clj
	- products/
  		- src/products/
		    core.cljs
		    state.cljs
		    action.cljs
		    - views/
		         detail.cljs
		         main.cljs
		         ...
		project.clj
	...

project.clj
lein
```

