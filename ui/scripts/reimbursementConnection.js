//let reimbursementResourceURL = "http://localhost:port/contextpath/resourcepath"; //CHANGE ME!\
//Example backend location:
let reimbursementResourceURL = "http://localhost:8080/Simpleservlets/Reimbursement";
//Note the context path is set to "/api" make sure to change that in the build config

async function newReimbursement(newReimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newReimbursement)
        }
    );

    return response;
}

async function getReimbursementStatus(s) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "mode": "status",
                "status": s
            }
        }
    );

    return response;
}

async function getReimbursementsUser(id) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "mode": "user",
                "user_id": id
            }
        }
    );

    return response;
}

async function getReimbursement(id) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "mode": "id",
                "req_id": id
            }
        }
    );

    return response;
}

async function getReimbursementAdmin(id) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "GET",
            headers: {
                "mode": "admin",
                "req_id": id
            }
        }
    );

    return response;
}

async function updateReimbursement(reimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(reimbursement)
        }
    );

    return response;
}

async function deleteReimbursement(reimbursement) {
    let response = await fetch(
        reimbursementResourceURL,
        {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "req_id": reimbursement
            },
            body: JSON.stringify(reimbursement)
        }
    );

    return response;
}