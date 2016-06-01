## Design patterns

Names and examples.

* Singleton: FreemarkerConfigProvider always provides the same Configuration object
* Proxy: app.resource.api.I*Resource interfaces are used to proxy http requests to resource classes in integration tests
* Dependency injection: @Inject done by Guice and Angular too

Implemented by underlying frameworks:

* Object (connection) pool: hibernate c3p0 keeps a bunch of connections around to be reused when possible
* Singleton: by Guice using @Singleton