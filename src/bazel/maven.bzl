load("@rules_jvm_external//:defs.bzl", "maven_install")

DROPWIZARD_VERSION = "2.0.10"
JUNIT_VERSION = "5.6.2"
DAGGER_VERSION = "2.27"

def install_maven_dependencies():
    maven_install(
        artifacts = [
            'io.dropwizard:dropwizard-core:%s' % DROPWIZARD_VERSION,
            'com.google.dagger:dagger:%s' % DAGGER_VERSION,
            'com.google.dagger:dagger-compiler:%s' % DAGGER_VERSION,
            'org.projectlombok:lombok:1.18.12',
            'javax.inject:javax.inject:1',
            'javax.ws.rs:javax.ws.rs-api:jar:2.1.1',
            'javax.validation:validation-api:2.0.1.Final',
            'org.junit.jupiter:junit-jupiter-api:%s' % JUNIT_VERSION,
            'org.junit.jupiter:junit-jupiter-params:%s' % JUNIT_VERSION,
            'org.junit.jupiter:junit-jupiter-engine:%s' % JUNIT_VERSION,
            'org.junit.platform:junit-platform-console:1.4.2',
        ],
        repositories = [
            "https://artifactory.booking.com/maven/"
        ],
        maven_install_json = '//:maven_install.json',
        fetch_sources = True,
        resolve_timeout = 3600,
        version_conflict_policy = "pinned",
        fail_on_missing_checksum = False,
    )
