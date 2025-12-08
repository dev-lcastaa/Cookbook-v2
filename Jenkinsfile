pipeline {
    agent any

    environment {
        FRONTEND_DIR = 'cookbook-frontend'
        BACKEND_DIR = 'cookbook-backend'
        JAVA_HOME = '/home/lcastaa/.sdkman/candidates/java/current'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Build Backend') {
            steps {
                dir("${BACKEND_DIR}") {
                    echo "Building Spring Boot backend..."
                    sh '''
                        chmod +x ./mvnw
                        ./mvnw clean package -DskipTests
                    '''
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

        stage('Docker Compose Up') {
            steps {
                echo "Building and deploying services with Docker Compose..."
                sh '''
                    # Navigate to project root if docker-compose.yml is there
                    docker-compose down --remove-orphans
                    docker-compose build
                    docker-compose up -d
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
