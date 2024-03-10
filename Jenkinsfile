pipeline {
    agent any

    tools {
        // Make sure Maven is configured in Jenkins Global Tool Configuration
        maven 'Maven' // 'Maven' is the name of the Maven installation to use.
        // Similarly, ensure JDK is configured
        jdk 'JDK' // 'JDK' is the name of the JDK installation to use.
    }

    environment {
        // Define environment variables if needed
        MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
    }

    stages {
        stage('Initialize') {
            steps {
                // Clean workspace
                sh 'echo Cleaning Workspace'
                deleteDir()
                // Checkout SCM
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    // Archive JUnit-formatted test reports
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
            post {
                success {
                    // Archive the built artifacts (adjust the '*.war' as needed)
                    archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            // Cleanup Maven repository after the build
            cleanWs()
        }
    }
}
