/*
 * @Github: https://github.com/Certseeds/CS203_DSAA_template_java
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-07-27 10:22:50
 * @LastEditors: nanoseeds
 * @LastEditTime: 2020-07-27 10:26:47
 */ 
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
public record ${NAME}(#[[$END$]]#) {
}
#set( $GITHUB_USER = "Certseeds" )
#parse("File Header.java")
#parse("LICENSE_MIT_JAVA.java")