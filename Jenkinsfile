pipeline {
    agent any

    tools {
        // Make sure Maven 3 is installed on Jenkins and configured with this name
        maven 'Maven 3'
        // Use JDK11, change this if you use a different version
        jdk 'JDK17'
    }

    stages {
        stage('Initialize') {
            steps {
                sh '''
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
                mvn -v
                '''
            }
        }

        stage('Checkout') {
            steps {
                // Checks out the code from your SCM. Adjust as necessary.
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Runs Maven package goal, which compiles your code, runs any tests, and packages it
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Build succeeded.'
                }
                failure {
                    echo 'Build failed.'
                }
            }
        }

        // Additional stages like 'Test', 'Deploy' can be added here
    }

    post {
        always {
            echo 'This will always run.'
        }
        success {
            echo 'Pipeline succeeded.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
