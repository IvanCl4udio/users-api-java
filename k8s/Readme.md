## How deploy into kubenetes cluster ##

Deploy application with:
```bash
kubectl apply -f deployment.yaml
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
helm install postgresql bitnami/postgresql -n database
```

