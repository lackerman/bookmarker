workspace(
    name = "bookmarker",
)

# Load all the external repositories
load("//src/bazel:repositories.bzl", "external_rules")
external_rules()

# Install external Maven dependencies
load("//src/bazel:maven.bzl", "install_maven_dependencies")
install_maven_dependencies()

# Lock/pin the Maven dependencies for reproducible builds.
load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()
