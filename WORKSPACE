git_repository(
    name = "org_pubref_rules_maven",
    commit = "25214e1",  # replace with latest version
    remote = "https://github.com/pubref/rules_maven",
)

load("@org_pubref_rules_maven//maven:rules.bzl", "maven_repositories", "maven_repository")

maven_repositories()

maven_repository(
    name = "artifact_repo",
    #    hermetic = False,
    transitive_deps = [
        "7c4f3c474fb2c041d8028740440937705ebb473a:ch.qos.logback:logback-classic:1.2.3",
        "864344400c3d4d92dfeb0a305dc87d953677c03c:ch.qos.logback:logback-core:1.2.3",
        "934c04d3cfef185a8008e7bf34331b79730a9d43:javax.annotation:javax.annotation-api:1.3.2",
        "fec5797a55b786184a537abd39c3fa1449d752d6:org.apache.logging.log4j:log4j-api:2.10.0",
        "f7e631ccf49cfc0aefa4a2a728da7d374c05bd3c:org.apache.logging.log4j:log4j-to-slf4j:2.10.0",
        "ad94df2a28d658a40dc27bbaff6a1ce5fbf04e9b:org.aspectj:aspectjweaver:1.8.13",
        "0af5364cd6679bfffb114f0dec8a157aaa283b76:org.slf4j:jul-to-slf4j:1.7.25",
        "da76ca59f6a57ee3102f8f9bd9cee742973efa8a:org.slf4j:slf4j-api:1.7.25",
        "4d214b08bbc74f03d404e8fc862102334e0f73d1:org.springframework.boot:spring-boot:2.0.6.RELEASE",
        "e8c88fd703dbdf79f94178a5018ad67d1ecb1d0d:org.springframework.boot:spring-boot-autoconfigure:2.0.6.RELEASE",
        "9d904a4387e076343a3478c32198ec7d9d633ff4:org.springframework.boot:spring-boot-starter:2.0.6.RELEASE",
        "f3db2e05267686fd97af5460a155bac4298fab4d:org.springframework.boot:spring-boot-starter-aop:2.0.6.RELEASE",
        "a730d62c8eb80cdc95e5089e47570d24731cf5dd:org.springframework.boot:spring-boot-starter-logging:2.0.6.RELEASE",
        "da65874f61aef9c25db794f493e50683346d3056:org.springframework:spring-aop:5.0.10.RELEASE",
        "8ec7da928082a123c50d5d70cd4b46382db27e8b:org.springframework:spring-beans:5.0.10.RELEASE",
        "6f53de0618219fefc2aebf92ba175e6b33e14ce0:org.springframework:spring-context:5.0.10.RELEASE",
        "cb270a60ceb573961588f511222984de64b4bc96:org.springframework:spring-core:5.0.10.RELEASE",
        "eaf8595e3bf18ef1aca1c233d9871552d258e38a:org.springframework:spring-expression:5.0.10.RELEASE",
        "8d6a766be91ae5613f2828eb9f77739a47ceb357:org.springframework:spring-jcl:5.0.10.RELEASE",
        "2d998d3d674b172a588e54ab619854d073f555b5:org.yaml:snakeyaml:1.19",
    ],
    deps = [
        "org.springframework.boot:spring-boot-starter-aop:2.0.6.RELEASE",
        #        "org.springframework.boot:spring-boot-starter-test:2.0.6.RELEASE",
    ],
)

load("@artifact_repo//:rules.bzl", "artifact_repo_compile")

artifact_repo_compile()

maven_repository(
    name = "artifact_repo_test",
    transitive_deps = [
        "7c4f3c474fb2c041d8028740440937705ebb473a:ch.qos.logback:logback-classic:1.2.3",
        "864344400c3d4d92dfeb0a305dc87d953677c03c:ch.qos.logback:logback-core:1.2.3",
        "765a4401ceb2dc8d40553c2075eb80a8fa35c2ae:com.jayway.jsonpath:json-path:2.4.0",
        "fa26d351fe62a6a17f5cda1287c1c6110dec413f:com.vaadin.external.google:android-json:0.0.20131108.vaadin1",
        "934c04d3cfef185a8008e7bf34331b79730a9d43:javax.annotation:javax.annotation-api:1.3.2",
        "2973d150c0dc1fefe998f834810d68f278ea58ec:junit:junit:4.12",
        "51218a01a882c04d0aba8c028179cce488bbcb58:net.bytebuddy:byte-buddy:1.7.9",
        "a6c65f9da7f467ee1f02ff2841ffd3155aee2fc9:net.bytebuddy:byte-buddy-agent:1.7.9",
        "c592b500269bfde36096641b01238a8350f8aa31:net.minidev:accessors-smart:1.2",
        "007396407491352ce4fa30de92efb158adb76b5b:net.minidev:json-smart:2.3",
        "fec5797a55b786184a537abd39c3fa1449d752d6:org.apache.logging.log4j:log4j-api:2.10.0",
        "f7e631ccf49cfc0aefa4a2a728da7d374c05bd3c:org.apache.logging.log4j:log4j-to-slf4j:2.10.0",
        "c5ce126b15f28d56cd8f960c1a6a058b9c9aea87:org.assertj:assertj-core:3.9.1",
        "42a25dc3219429f0e5d060061f71acb49bf010a0:org.hamcrest:hamcrest-core:1.3",
        "4785a3c21320980282f9f33d0d1264a69040538f:org.hamcrest:hamcrest-library:1.3",
        "b84bfbbc29cd22c9529409627af6ea2897f4fa85:org.mockito:mockito-core:2.15.0",
        "639033469776fd37c08358c6b92a4761feb2af4b:org.objenesis:objenesis:2.6",
        "0da08b8cce7bbf903602a25a3a163ae252435795:org.ow2.asm:asm:5.0.4",
        "6c9d5fe2f59da598d9aefc1cfc6528ff3cf32df3:org.skyscreamer:jsonassert:1.5.0",
        "0af5364cd6679bfffb114f0dec8a157aaa283b76:org.slf4j:jul-to-slf4j:1.7.25",
        "da76ca59f6a57ee3102f8f9bd9cee742973efa8a:org.slf4j:slf4j-api:1.7.25",
        "4d214b08bbc74f03d404e8fc862102334e0f73d1:org.springframework.boot:spring-boot:2.0.6.RELEASE",
        "e8c88fd703dbdf79f94178a5018ad67d1ecb1d0d:org.springframework.boot:spring-boot-autoconfigure:2.0.6.RELEASE",
        "9d904a4387e076343a3478c32198ec7d9d633ff4:org.springframework.boot:spring-boot-starter:2.0.6.RELEASE",
        "a730d62c8eb80cdc95e5089e47570d24731cf5dd:org.springframework.boot:spring-boot-starter-logging:2.0.6.RELEASE",
        "f92fd015cfc9a9bd6ae653dca8d4ec5b83d4ba00:org.springframework.boot:spring-boot-starter-test:2.0.6.RELEASE",
        "33a53ca6d55f35b3a89320c260262a5310ba8afc:org.springframework.boot:spring-boot-test:2.0.6.RELEASE",
        "b0eb15474e795850dfa59b01ee6a83d7a39e3645:org.springframework.boot:spring-boot-test-autoconfigure:2.0.6.RELEASE",
        "da65874f61aef9c25db794f493e50683346d3056:org.springframework:spring-aop:5.0.10.RELEASE",
        "8ec7da928082a123c50d5d70cd4b46382db27e8b:org.springframework:spring-beans:5.0.10.RELEASE",
        "6f53de0618219fefc2aebf92ba175e6b33e14ce0:org.springframework:spring-context:5.0.10.RELEASE",
        "cb270a60ceb573961588f511222984de64b4bc96:org.springframework:spring-core:5.0.10.RELEASE",
        "eaf8595e3bf18ef1aca1c233d9871552d258e38a:org.springframework:spring-expression:5.0.10.RELEASE",
        "8d6a766be91ae5613f2828eb9f77739a47ceb357:org.springframework:spring-jcl:5.0.10.RELEASE",
        "1d2d272073f36c689c693ec3ec94d7ad7e136a2e:org.springframework:spring-test:5.0.10.RELEASE",
        "4ffdb346572a7356f7521cd3119ce5287d2e339d:org.xmlunit:xmlunit-core:2.5.1",
        "2d998d3d674b172a588e54ab619854d073f555b5:org.yaml:snakeyaml:1.19",
    ],
    deps = [
        "org.springframework.boot:spring-boot-starter-test:2.0.6.RELEASE",
    ],
)

#maven_repository(
#    name = "artifact_repo",
#    deps = [
#        "junit:junit:4.12",
#        #        "org.mockito:mockito-core:2.23.4",
#        "org.springframework.boot:spring-boot-starter-aop:2.0.6.RELEASE",
#        "org.springframework.boot:spring-boot-starter-test:2.0.6.RELEASE",
#    ],
#)

#maven_jar(
#    name = "org_springframework_boot_spring_boot_starter_aop",
#    artifact = "org.springframework.boot:spring-boot-starter-aop:2.0.6.RELEASE",
#)
#
#maven_jar(
#    name = "org_springframework_boot_spring_boot_starter_test",
#    artifact = "org.springframework.boot:spring-boot-starter-test:2.0.6.RELEASE",
#)
#
#maven_jar(
#    name = "org_mockito_mockito_core",
#    artifact = "org.mockito:mockito-core:2.23.4",
#)

load("@artifact_repo_test//:rules.bzl", "artifact_repo_test_compile")

artifact_repo_test_compile()
