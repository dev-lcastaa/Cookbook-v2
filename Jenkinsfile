pipeline {
    agent any

    environment {
            SCRIPT_DIR = 'aql-pipeline-scripts'
            FRONTEND_DIR = 'cookbook-frontend'
            BACKEND_DIR = 'cookbook-backend'
        }

        stages {
            stage('Clone Pipeline Scripts') {
                steps {
                    // Clone the scripts repo
                    dir("${SCRIPT_DIR}") {
                        git branch: 'main', url: 'https://github.com/AspireQLabs/aql-pipeline-scripts.git'
                    }
                }
            }

        stage('Build Frontend') {
            steps {
                dir("${FRONTEND_DIR}") {
                    echo "Installing frontend dependencies..."
                    sh 'npm install'
                    echo "Building frontend..."
                    sh 'npm run build'
                }
            }
        }

        stage('Clean up old containers') {
            steps{
                echo "Running clean up script"
                sh """
                   chmod +x ${SCRIPT_DIR}/container-cleanup.sh
                   ${SCRIPT_DIR}/container-cleanup.sh ${BACKEND_DIR} ${FRONTEND_DIR} cookbook-database
                """
            }
        }

        stage('Docker Compose Up') {
            steps {
                echo "Building and deploying services with Docker Compose..."
                sh '''
                    # Navigate to project root if docker-compose.yml is there
                    docker compose down --remove-orphans
                    docker compose build
                    docker compose up -d
                '''
            }
        }
    }

    post {
        success {
            echo "Build, test, and deploy completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check logs for errors."
        }
    }
}
