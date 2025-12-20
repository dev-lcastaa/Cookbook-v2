def notifyDiscord(String message) {
    withCredentials([string(credentialsId: 'discord-webhook', variable: 'DISCORD_WEBHOOK')]) {
        sh """
          curl -H "Content-Type: application/json" \
               -X POST \
               -d "{\\"content\\": \\"${message}\\"}" \
               $DISCORD_WEBHOOK
        """
    }
}

pipeline {
    agent any

    environment {
        SCRIPT_DIR   = 'aql-pipeline-scripts'
        FRONTEND_DIR = 'cookbook-frontend'
        BACKEND_DIR  = 'cookbook-backend'
    }

    stages {

        stage('Initializing Pipeline') {
            steps {
                script {
                    notifyDiscord("Initializing Pipeline")
                }

                echo "Cloning pipeline scripts..."
                dir("${SCRIPT_DIR}") {
                    git branch: 'main', url: 'https://github.com/AspireQLabs/aql-pipeline-scripts.git'
                }
            }
        }

        stage('Building Frontend') {
            steps {
                script {
                    notifyDiscord("- Building Frontend")
                }

                dir("${FRONTEND_DIR}") {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }


//         stage('Backend Tests & Coverage Tollgate') {
//            steps {
//                script {
//                    notifyDiscord("- Running Backend Tests & Coverage Tollgate")
//                }
//                dir("${BACKEND_DIR}") {
//                    sh 'chmod +x mvnw'
//                    sh './mvnw clean verify'
//                }
//            }
//         }

        stage('Preparing Environment') {
            steps {
                script {
                    notifyDiscord("- Preparing Environment")
                }

                sh """
                   chmod +x ${SCRIPT_DIR}/container-cleanup.sh
                   ${SCRIPT_DIR}/container-cleanup.sh ${BACKEND_DIR} ${FRONTEND_DIR} cookbook-database
                """
            }
        }

        stage('Deploying to Environment') {
            steps {
                script {
                    notifyDiscord("- Deploying to Environment")
                }

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
            script {
                notifyDiscord("Pipeline finished with success!")
            }
        }
        failure {
            script {
                notifyDiscord("Pipeline failed. Check logs.")
            }
        }
    }
}
