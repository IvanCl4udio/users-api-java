## How deploy into kubenetes cluster ##

First create the namespace crud-users with:
```bash
kubectl apply -f namespace.yaml
```

Create a secret that stores the database credentials:
```bash
kubectl apply -f secret.yaml
```

Deploy application with:
```bash
kubectl apply -f deployment.yaml
```

Create a service with:
```bash
kubectl apply -f service.yaml
```

Create an ingress with:
```bash
kubectl apply -f ingress.yaml
```

## Assumptions ##

The application uses a postgresql database that was deployed on cluster using the helm chart: bitnami/postgresql

To install run the following steps
```bash
# Add bitnami repository
helm repo add bitnami https://charts.bitnami.com/bitnami
# Create a namespace for postgresql
kubectl create ns postgresql
# Run the installation
helm install postgresql bitnami/postgresql -n postgresql
```

