const apiUrl = 'http://localhost:8080/students'; 


function fetchStudents() {
    fetch(apiUrl)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById('studentTable');
            table.innerHTML = '';
            data.forEach(student => {
                table.innerHTML += `
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.age}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" onclick="editStudent(${student.id})">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteStudent(${student.id})">Delete</button>
                        </td>
                    </tr>
                `;
            });
        });
}


document.getElementById('studentForm').addEventListener('submit', function(e){
    e.preventDefault();
    const id = document.getElementById('studentId').value;
    const studentData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        age: parseInt(document.getElementById('age').value)
    };

    if(id){ 
        fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(studentData)
        }).then(() => {
            fetchStudents();
            this.reset();
            document.getElementById('studentId').value = '';
        });
    } else { 
        fetch(apiUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(studentData)
        }).then(() => {
            fetchStudents();
            this.reset();
        });
    }
});

function editStudent(id){
    fetch(`${apiUrl}/${id}`) 
        .then(res => res.json())
        .then(student => {
            document.getElementById('studentId').value = student.id;
            document.getElementById('name').value = student.name;
            document.getElementById('email').value = student.email;
            document.getElementById('age').value = student.age;
        });
}


function deleteStudent(id){
    fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
        .then(() => fetchStudents());
}

fetchStudents();
