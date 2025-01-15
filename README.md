Gradle project reproducing an issue with the configuration cache since the upgrade from version 8.10.2 to 8.11 of the gradle wrapper.

Running `./gradlew` (and any task) will throw an error like this:

```
❯ ./gradlew
Calculating task graph as no cached configuration is available for tasks:

FAILURE: Build failed with an exception.

* What went wrong:
A problem occurred configuring project ':module2'.
> Unexpected delegating class loader 'CachingClassLoader(org.gradle.internal.classloader.MultiParentClassLoader@666c833e)' of type 'org.gradle.internal.classloader.CachingClassLoader' with id 'ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:]:buildSrc[:](export)}' for scope 'ClassLoaderScop
eIdentifier{coreAndPlugins:settings[:]:buildSrc[:]}' with classpath '[/Users/user/.gradle/caches/8.11/dependencies-accessors/cd3b170f8c5cf4e81ce7d3902ea46ce80931e2a1/classes]'.
  These are the known class loaders:
        - VisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:]:buildSrc[:]:kotlin-dsl:/Users/user/StudioProjects/gradle-settings-libsversions-bug/build.gradle.kts:Project/TopLevel/stage1(local)})
        - VisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:]:buildSrc[:]:root-project[:]:project-library:kotlin-dsl:/Users/user/StudioProjects/gradle-settings-libsversions-bug/library/build.gradle.kts:Project/TopLevel/stage2(local)})
        - VisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:]:kotlin-dsl:/Users/user/StudioProjects/gradle-settings-libsversions-bug/settings.gradle.kts:Settings/TopLevel/stage2(local)})
        - VisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:]:buildSrc[:]:kotlin-dsl:/Users/user/StudioProjects/gradle-settings-libsversions-bug/library/build.gradle.kts:Project/TopLevel/stage1(local)})
        - VisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:]:buildSrc[:](export)})
        - InstrumentingVisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:](export)})
        - VisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:settings[:]:buildSrc[:]:kotlin-dsl:/Users/user/StudioProjects/gradle-settings-libsversions-bug/module1/build.gradle.kts:Project/TopLevel/stage1(local)})
        - VisitableURLClassLoader(ClassLoaderScopeIdentifier.Id{coreAndPlugins:kotlin-dsl:/Users/user/StudioProjects/gradle-settings-libsversions-bug/settings.gradle.kts:Settings/TopLevel/stage1(local)})
  Please report this error, run './gradlew --stop' and try again.

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Get more help at https://help.gradle.org.

BUILD FAILED in 549ms
Configuration cache entry stored.
```

The workarounds I've found are:

1. Running without configuration cache by either adding `--no-configuration-cache` or removing the line in `gradle.properties`.
2. Removing the `test = 1` line in `libs.versions.toml` (or deleting the file).
3. Removing any of the two applied plugins in `settings.gradle.kts`.
4. Removing `module1` or `module2` (this is a weird one).

But we'd like to keep using this setup, which is working in 8.10.2

