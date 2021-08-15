#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
public @interface ${NAME} {
}
#set( $GITHUB_USER = "Certseeds" )
#parse("File Header.java")
#parse("LICENSE_MIT_JAVA.java")