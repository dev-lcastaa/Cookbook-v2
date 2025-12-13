pipeline {
    agent any

    environment {
            SCRIPT_DIR = 'aql-pipeline-scripts'
            FRONTEND_DIR = 'cookbook-frontend'
            BACKEND_DIR = 'cookbook-backend'
        }

        stages {
            stage('Initializing Pipeline') {
                script {
                    notifyDiscord("Initializing Pipeline")
                }
                steps {
                    echo "Cloning pipeline scripts..."
                    dir("${SCRIPT_DIR}") {
                        git branch: 'main', url: 'https://github.com/AspireQLabs/aql-pipeline-scripts.git'
                    }
                }
            }

        stage('Building Frontend') {
            script {
                  notifyDiscord("- Building Frontend")
            }
            steps {
                dir("${FRONTEND_DIR}") {
                    echo "Installing frontend dependencies..."
                    sh 'npm install'
                    echo "Building frontend..."
                    sh 'npm run build'
                }
            }
        }

        stage('Preparing Environment') {
            script {
                  notifyDiscord("- Preparing Environment")
            }
            steps{
                echo "Cleaning old containers..."
                sh """
                   chmod +x ${SCRIPT_DIR}/container-cleanup.sh
                   ${SCRIPT_DIR}/container-cleanup.sh ${BACKEND_DIR} ${FRONTEND_DIR} cookbook-database
                """
            }
        }

        stage('Deploying to Environment') {
            script {
                  notifyDiscord("- Deploying to Environment")
            }
            steps {
                echo "Building and deploying services with Docker Compose..."
                sh '''
                    docker compose down --remove-orphans
                    docker compose build
                    docker compose up -d
                '''
            }
        }
    }

    post {
        success {
            echo "Pipeline finished with success...!"
            script {
                  notifyDiscord("Pipeline finished with success...!")
            }
        }
        failure {
            echo "Pipeline failed. Check logs for errors."
            script {
                  notifyDiscord("Pipeline failed. Check logs for errors.")
            }
        }
    }


    def notifyDiscord(String message) {
      withCredentials([string(credentialsId: 'discord-webhook', variable: 'DISCORD_WEBHOOK')]) {
        sh """
          curl -H "Content-Type: application/json" \\
               -X POST \\
               -d "{\\"content\\": \\"${message}\\"}" \\
               $DISCORD_WEBHOOK
        """
      }
}
