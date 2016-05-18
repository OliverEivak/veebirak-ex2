# exercise-2 front-end

## Apache setup
The project uses HTML5 mode to avoid the # in URLs. Apache needs to be set up to rewrite the URLs.
In site configuration add the following under DocumentRoot (requires mod_rewrite):

```
<Directory />
    Order deny,allow
    Allow from all

    RewriteEngine on

    # Don't rewrite files or directories
    RewriteCond %{REQUEST_FILENAME} -f [OR]
    RewriteCond %{REQUEST_FILENAME} -d
    RewriteRule ^ - [L]

    # Rewrite everything else to index.html to allow html5 state links
    RewriteRule ^ index.html [L]
</Directory>
```
