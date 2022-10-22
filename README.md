# Challengers App Env Mapping



## Run Spring Boot application
```
mvn clean install
```
```
mvn spring-boot:run
```
Once the application is up test this by the following GET,PUT,POST,DEL Methods

- In Insomnia or Postman send a POST request `http://localhost:8080/api/components` using the following json body

```{
	"name": "EmployeeApp",
	"envname": "DEV",
	"description": "Employee List",
    "ucdteamName": "DOE3_EMPAPPDEVTEAM",
	"centralapp": "true",
    "upstreamapp": "false",
    "downstreamapp" : "false"
}
```
- Response
```
{{
	"id": "63538ab9b4881176a50d25bc",
	"name": "EmployeeApp",
	"description": "Employee List",
	"centralapp": true,
	"downstreamapp": false,
	"upstreamapp": false,
	"envName": null,
	"ucdteamName": "DOE3_EMPAPPDEVTEAM"
}
```
- GET api url: `http://localhost:8080/api/components`
- DELETE api url: `http://localhost:8080/api/components`

- The Flow is as follows.
When a deployment of a  `EmployeeApp` component is triggered by a member of `DOE3_EMPAPPDEVTEAM` team member, after sucecssful deployment we expect the following data to be present on a MongoDB Table 
  - component name
  - environment name
  - release version
  - team name
  - date of deployment

Every team can go and find an APP  where the solution is hosted. 
We provide a radio button to the own app `EmployeeApp` by default
When a button is clicked the rows of all the apps where EmployeeApp is marked with upstream, downstream or central would show up group by `ucdteamname`. 
e.g. 
- for UCD TEAM `DOE3_EMPAPPDEVTEAM` `centralapp=true` for `EmployeeApp` but they can keep `SalaryApp` as upstream or downstream.
- for UCD TEAM `DOE3_SALARYAPPDEVTEAM` `centralapp=true` for `SalaryApp` but they can keep `EmployeeApp` as upstream or downstream.

So when a query happens eith Mongo Repository `findByUCDTeamName(String ucdteamname);` both returns two rows and they can be rendered in to graphs for visualization.