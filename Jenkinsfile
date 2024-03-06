pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                // Checks out the code from your SCM. Adjust as necessary.
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Runs Maven package goal, which compiles your code, runs any tests, and packages it
                echo 'Build build.'
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
