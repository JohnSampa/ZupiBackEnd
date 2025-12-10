childId = localStorage.getItem('childId');

const childData = await fetch(`http://localhost:8080/child/${childId}`, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
});

document.getElementById('childName').textContent = childData.name;