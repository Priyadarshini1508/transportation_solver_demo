1. The Clone Step
In IntelliJ, they go to File > New > Project from Version Control. They paste your URL:
https://github.com/Priyadarshini1508/transportation_solver_demo.git

2. The Resulting Folder Structure
Once it finishes, their IntelliJ Project tool window (on the left) will look like this:

majorproject_demo (The main root)

📂 transportion_demo_backend (The Java Spring Boot part)

📂 transportion_demo_ui (The Frontend part)

3. How they "Work" on both
IntelliJ is smart, but since these are two different types of projects, they should do this:

For the Backend: IntelliJ will usually see the pom.xml inside the backend folder and ask "Load Maven Project?". They must click Yes. This lets them run the Java code.

For the Frontend: They can just open the built-in Terminal at the bottom of IntelliJ, cd transportion_demo_ui, and run npm start.
