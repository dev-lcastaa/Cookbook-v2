pipeline {
    agent any

    environment {
        IMAGE_NAME = "cookbook-app"
    }

    stages {
        stage('Hello World') {
            steps {
                script {
                   echo 'Hi this is from the pipeline with a change'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Build and deploy successful!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
