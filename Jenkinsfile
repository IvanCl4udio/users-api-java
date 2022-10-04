podTemplate(containers: [containerTemplate(name: 'maven', image: 'maven', command: 'sleep', args: 'infinity')]) {
  node(POD_LABEL) {
      checkout scm
        container('maven') {
              sh 'type mvn'
              sh 'mvn -B -ntp -Dmaven.test.failure.ignore verify'
                  
        }
            junit '**/target/surefire-reports/TEST-*.xml'
              
  }
  
}
