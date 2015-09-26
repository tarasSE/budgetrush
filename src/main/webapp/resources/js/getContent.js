function getContent(url, tableId) {

    $.getJSON(url + '/?access_token=' + getToken(), function (data) {

        $("#"+tableId).find("tr").remove();

        var tableRef = document.getElementById(tableId);

        var tr = tableRef.insertRow();
        if ('id' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('ID'));
        }

        if ('name' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Name'));
        }

        if ('password' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Password'));
        }

        if ('user' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('User'));
        }

        if ('currency' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Currency'));
        }

        if ('parent' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Parent'));
        }

        if ('description' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Description'));
        }

        if ('shortName' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Short name'));
        }

        if ('code' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Code'));
        }

        if ('symbol' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Symbol'));
        }

        if ('amount' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Amount'));
        }

        if ('date' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Date'));
        }

        if ('type' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Type'));
        }

        if ('contractor' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Contractor'));
        }

        if ('account' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Account'));
        }

        if ('category' in data[0]) {
            tr.insertCell().appendChild(document.createTextNode('Category'));
        }

        for (var i = data.length - 1; i >= 0; i--) {
            var currentRow = tableRef.insertRow(1);
            var cellNumber = 0;

            if ('id' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].id));
            }

            if ('name' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].name));
            }

            if ('password' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].password));
            }

            if ('user' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].user.name));
            }

            if ('currency' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].currency.name));
            }

            if ('parent' in data[0]) {
                if (false) {
                    currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].parent.id));
                }
                else{
                    currentRow.insertCell(cellNumber++).appendChild(document.createTextNode('TODO'));
                }
            }

            if ('description' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].description));
            }

            if ('shortName' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].shortName));
            }

            if ('code' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].code));
            }

            if ('symbol' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].symbol));
            }

            if ('amount' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].amount));
            }

            if ('date' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].date));
            }

            if ('type' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].type));
            }

            if ('contractor' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].contractor.name));
            }

            if ('account' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].account.name));
            }

            if ('category' in data[0]) {
                currentRow.insertCell(cellNumber++).appendChild(document.createTextNode(data[i].category.name));
            }

        }

    });
}