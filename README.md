## Work Insights
### An application providing statistics about employees and projects   
Features:
* Employees
    * Add employees to database
    * View employees
    * Edit employees
    * Remove employees from database
* Projects
    * Add projects to database
    * View projects
    * Edit projects
    * Remove projects from database
* Relationships
    * Add relationships between employees and projects to database via CSV files in the format: "Employee ID, Project ID, Start date, End date"
    * Only existing employees and projects can be connected
    * If an employee is deleted, the relationship is deleted
    * If a project is deleted, the relationship is deleted
* Statistics
    * Get the pair of employees who have worked together on common projects for the longest period of time and the time for each of those projects.
    * Data is taken from database
