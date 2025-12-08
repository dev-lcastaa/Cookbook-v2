pipeline {
    agent any

    environment {
        FRONTEND_DIR = 'cookbook-frontend'
        BACKEND_DIR = 'cookbook-backend'
        DOCKER_FRONTEND_IMAGE = 'cookbook-frontend:latest'
        DOCKER_BACKEND_IMAGE = 'cookbook-backend:latest'
    }

    stages {
        stage('Build Backend') {
            steps {
                dir("${BACKEND_DIR}") {
                    echo "Building Spring Boot backend..."
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Test Backend') {
            steps {
                dir("${BACKEND_DIR}") {
                    echo "Running backend tests..."
                    sh 'mvn test'
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

        stage('Docker Build & Deploy') {
            steps {
                echo "Building Docker images..."
                sh """
                    docker build -t ${DOCKER_BACKEND_IMAGE} ${BACKEND_DIR}
                    docker build -t ${DOCKER_FRONTEND_IMAGE} ${FRONTEND_DIR}
                """
                echo "Running containers..."
                sh """
                    docker rm -f cookbook-backend || true
                    docker rm -f cookbook-frontend || true
                    docker run -d --name cookbook-backend -p 8080:8080 ${DOCKER_BACKEND_IMAGE}
                    docker run -d --name cookbook-frontend -p 3000:3000 ${DOCKER_FRONTEND_IMAGE}
                """
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
