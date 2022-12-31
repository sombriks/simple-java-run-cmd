# Publishing on maven central

The tl;dr version about the process of publishing this package on maven central:

- Code the project itself
- Configure your [pom.xml](../pom.xml) properly
- Configure a [settings.xml](../settings.xml) too
- Setup a gpg signature
- Create a jira account on https://issues.sonatype.org
- Open a _new project ticket_ as described on
  https://central.sonatype.org/publish/publish-guide/#a-complete-example-pom
  - example: https://issues.sonatype.org/browse/OSSRH-87452 
- Run `mvn verify deploy -s settings.xml -Dusername=your-sonatype-username -Dpassword=your-sonatype-password`
  and then [go see your packaging in stage phase](https://s01.oss.sonatype.org/#stagingRepositories)
  - 'close' the staging repository and see if it passes validations
  - 'promote' your repo and see it going live
- Wait for proper publishing and then use your package on other projects.

## Notes on pom structure

Do the proper configuration for `distributionManagement`. See
https://help.sonatype.com/repomanager2/staging-releases/configuring-your-project-for-deployment
for more details. The [pom.xml](../pom.xml) present on this project is good
enough.

You will need however a dedicated [settings.xml](../settings.xml) to set up
authentication for staging server.

## Notes on sonatype account

On this current setup, username and password for staging upload are provided by
command line arguments redirected to the provided settings.xml file. 

## Notes on gpg signature

Right now the signature is using the default signature in keychain and not doing
that via CI of any kind, but depending on manual action from developer.

How to specify keychain, key and password for signature still as a work in
progress.
