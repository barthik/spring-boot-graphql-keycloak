# Spring Boot 2 + GraphQL + Keycloak

Prerequisites:
- Keycloak 4.5.0-Final standalone
- `graphql` realm
- `graphql-client` client
  - Access type: confidential
  - Valid redirect URIs: *
  - Base URL: localhost:40000
  - Web Origins: *
- Use credentials (client secret) of your realm in the project
- Create user for the `graphql` realm and roles

Keycloak admin console available at `http://localhost:8090/auth/admin/`

Unauthorized requests are redirected to the IDP at `http://localhost:8090/auth/realms/graphql/protocol/openid-connect/auth?response_type=code&client_id=graphql-client&redirect_uri=uri&state=state&login=true&scope=openid`

IDP profile edit available at `http://localhost:8090/auth/realms/graphql/account`

Get access token:

```cmd
curl -X POST -s "localhost:8090/auth/realms/graphql/protocol/openid-connect/token" -H "Content-Type:application/x-www-form-urlencoded" -d "username=adam.barton@embedit.cz" -d "password=pass" -d "grant_type=password" -d "client_id=graphql-client" -d "client_secret=c7e319c4-6f31-4512-9f33-294f7ec04330"
```

Response example:

```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ6OEpvb3AyOEZsRTNBMEZkTTBHWjRwOGgtQUZvc1R5NGE0SDNxVjZwMjZZIn0.eyJqdGkiOiI5MDViNjU2Ny1lMmFkLTQxMGUtYTkzYy0zYWUxYzUxMzAxYjAiLCJleHAiOjE1NDA3OTc2NzAsIm5iZiI6MCwiaWF0IjoxNTQwNzk1ODcwLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwOTAvYXV0aC9yZWFsbXMvZ3JhcGhxbCIsImF1ZCI6ImdyYXBocWwtY2xpZW50Iiwic3ViIjoiMDMyOTZmYmQtZTNhNC00M2I1LWIwZDgtMDkyZjI0YWUxMjk4IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZ3JhcGhxbC1jbGllbnQiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiJiYTE2NTJhZS0yNjdlLTQwNDMtOGNiYi0yM2Q2ZmJjOThhZTIiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJBZGFtIEJhcnRvxYgiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhZGFtLmJhcnRvbkBlbWJlZGl0LmN6IiwiZ2l2ZW5fbmFtZSI6IkFkYW0iLCJmYW1pbHlfbmFtZSI6IkJhcnRvxYgiLCJlbWFpbCI6ImFkYW1hZGFtLmJhcnRvbkBlbWJlZGl0LmN6In0.UJ0AcS86mabniyBiNE2mIv4UIpZH0S1-paMkPRBOB9AofmfFJn1tWR-Y_Z95TfsW-qsN0W2xIXuauUongARcfDF-y5P2vVF8JQ5-pTcaxrA5CcIQwciFBw-yx5oGrlWZh9FlTqxQA156mG3b2_DYahlXwgtH089VqWo0i7DXVgHNhqpV0zICxjApw3FHEMkVAMEEPfLFReNgbSh5awbRaLDb_tHu3BrNraqq80RVlJ-pLHriLDmm8yua0FRigqze4NaHQAxEKkUo3AiOfbJOuyRCq7jNRhyKXZwfSzXVrQlK17dHedktBYqNtzZlR-x2Obm8Cq121d82deAoF2RyUQ",
    "expires_in": 1800,
    "refresh_expires_in": 1800,
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIyMTZhOTRkOS1jZDJhLTQwMTAtODk5Mi0yMDBkZmQxODkyNjIifQ.eyJqdGkiOiI0YmE5N2QxZS00OTkyLTQ2ZjYtYjE2NC01OGYxMGUwNmE5NGYiLCJleHAiOjE1NDA3OTc2NzAsIm5iZiI6MCwiaWF0IjoxNTQwNzk1ODcwLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwOTAvYXV0aC9yZWFsbXMvZ3JhcGhxbCIsImF1ZCI6ImdyYXBocWwtY2xpZW50Iiwic3ViIjoiMDMyOTZmYmQtZTNhNC00M2I1LWIwZDgtMDkyZjI0YWUxMjk4IiwidHlwIjoiUmVmcmVzaCIsImF6cCI6ImdyYXBocWwtY2xpZW50IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiYmExNjUyYWUtMjY3ZS00MDQzLThjYmItMjNkNmZiYzk4YWUyIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUifQ.11LN_wgE_GIFaJwqIfP3dBVtVoNOFx7_A2uhbCsLO7g",
    "token_type": "bearer",
    "not-before-policy": 1540554823,
    "session_state": "ba1652ae-267e-4043-8cbb-23d6fbc98ae2",
    "scope": "email profile"
}
```

Get user info:

```cmd
curl -X GET -s "localhost:8090/auth/realms/graphql/protocol/openid-connect/userinfo" -H "Authorization:Bearer {{access_token}}" | jq .
```

Response example:

```json
{
    "sub": "03296fbd-e3a4-43b5-b0d8-092f24ae1298",
    "email_verified": true,
    "name": "A. Bartholomew",
    "preferred_username": "a.bartholomew@domain.tld",
    "given_name": "A.",
    "family_name": "Bartholomew",
    "email": "a.bartholomew@domain.tld"
}
```

Get GraphQL schema:

```cmd
curl -GET -s "http://localhost:40000/graphql/schema.json" -H "Authorization:Bearer {{access_token}}" -H "Content-Type:application/json" | jq . 
```

Response example:

```json
{
    "data": {
        "__schema": {
            "queryType": {
                "name": "Query"
            },
            "mutationType": {
                "name": "Mutation"
            },
            "subscriptionType": null,
            "types": [
                {
                    "kind": "OBJECT",
                    "name": "Query",
                    "description": "",
                    "fields": [
                        {
                            "name": "roles",
                            "description": "Finds all availible roles",
                            "args": [],
                            "type": {
                                "kind": "NON_NULL",
                                "name": null,
                                "ofType": {
                                    "kind": "LIST",
                                    "name": null,
                                    "ofType": {
                                        "kind": "OBJECT",
                                        "name": "Role",
                                        "ofType": null
                                    }
                                }
                            },
                            "isDeprecated": false,
                            "deprecationReason": null
                        },
                        {
                            "name": "rolesCount",
                            "description": "Counts all availible roles",
                            "args": [],
                            "type": {
                                "kind": "NON_NULL",
                                "name": null,
                                "ofType": {
                                    "kind": "SCALAR",
                                    "name": "Long",
                                    "ofType": null
                                }
                            },
                            "isDeprecated": false,
                            "deprecationReason": null
                        },
            ...
            ...
            ...
```

GraphQL endpoint:

```cmd
curl -GET -s "http://localhost:40000/graphql" -H "Authorization:Bearer {{access_token}}" -H "Content-Type:application/json" -b "{\"query\": \"{users {id username email requestCount role {id name description} state}}\"}" | jq . 
```

Response example:

```json
{
  "data": {
    "users": [
      {
        "id": "3c1ba74f-ac74-4ccb-86d3-72787da51a2e",
        "email": "barthik@domain.tld",
        "username": "barthik",
        "requestCount": 100,
        "role": {
          "id": "b7296e82-a2e2-4a99-b82a-1fb5e18295ac",
          "name": "Admin",
          "description": "Admin user role"
        }
      },
      {
        "id": "f3f1ca76-c46e-47e1-a521-8ad8da2f075b",
        "email": "frodo@lotr.tld",
        "username": "frodo",
        "requestCount": 5,
        "role": {
          "id": "c524d8c5-a6cb-4696-8047-a18f22e4b480",
          "name": "User",
          "description": "Regular user role"
        }
      },
      {
        "id": "2ed43eac-b19c-4d89-9c6d-0d8a1b9751eb",
        "email": "pepazdepa@depo.tld",
        "username": "pepazdepa",
        "requestCount": 0,
        "role": {
          "id": "c524d8c5-a6cb-4696-8047-a18f22e4b480",
          "name": "User",
          "description": "Regular user role"
        }
      }
    ]
  }
}
```

GraphiQL available at `http://localhost:40000/graphiql`