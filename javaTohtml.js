//Generel kode der anvendes flere steder i programmet skrives herunder
//Finder year og currentWeekNumber, som skal anvendes længere nede i koden, til at udstille rul (Sprint) perioderne 
// Create a new Date object
const now = new Date();

//Jeg skriver noget

const year = now.getFullYear();

// Determine the first day of the year
const firstDayOfYear = new Date(year, 0, 1);

// Determine the current day of the year (subtract the first day of the year from the current date and add 1)
const currentDayOfYear = Math.ceil((now - firstDayOfYear) / 86400000) + 1;

// Determine the current week number (divide the current day of the year by 7 and round up)
const currentWeekNumber = Math.ceil(currentDayOfYear / 7);

//console.log(currentWeekNumber); // Output the current week number
//-------------------------------------------------------------------------------------------------------------------------------------

//Programmet der snakker sammen med API'et og transformere data til HTML tables
function apiDataToHtmlTable(apiUrl, querySelectorHtml){
    //fetch kalder routen for API'et, dette sker via en promise, hvori der kan arbejdes med resultatet, når det er leveret. 
    fetch(apiUrl).then(response => {
        return response.json();
    }).then(data => {
        // Data omskrives til et json format
        jsonData = JSON.parse(data)
        //opetter en variable til at gemme querySelector
        let placeholderTableData = document.querySelector(querySelectorHtml);
        try {
            objectHeaders = Object.keys(jsonData[0]) //Finder overskrifter
        } catch (error) {
            //console.log("Der var ikke noget data fra Api kaldet til at danne en json")
        }
        //Get headers from jsonData
        
        //Sætter counter til at håndteres iterationerne i det jsonData forloop'et
        counter = 1;
        //Sætter html tabellen attributes, således disse kan håndteres senere i CSS.
        CombinedHtmlTable = "<table id=\"GeneratedTables\" style=\"width:50%\">\n\t<tr>\n"
        //Opretter placeholder der skal indeholde header data der bliver lavet i loopet
        htmlTableHeader = '';

        //Get items from json - Opretter header data. 
        for(item in objectHeaders){
            //console.log(objectHeaders[item])
            htmlTableHeader = htmlTableHeader+"\t\t<th>"+objectHeaders[item]+"</th>\n"
        }
        //Gemmer det samlede resultat.
        CombinedHtmlTable=CombinedHtmlTable+htmlTableHeader+"\t</tr>\n"
        
        //Opretter placeholder for TableRow data.
        htmlTableRow = ''
        for(var key in jsonData) {
            //console.log(key)
            htmlTableData = ''
            for (var key1 in jsonData[key]) {
                htmlTableData = htmlTableData+"<td>"+jsonData[key][key1]+"</td>\n\t\t"
                //console.log(jsonData[key][key1])
            }
            htmlTableRow = htmlTableRow+"\t<tr id='"+counter+"' onclick='myFunction(this)'>\n\t\t"+htmlTableData+"\n\t</tr>\n"
            counter=counter+1
        }
        //Samler alt html table data.  
        CombinedHtmlTable = CombinedHtmlTable+htmlTableRow+"</table>\n"   
        //Indsætter placeholder data for html table, og skriver det til placeholderen for querySelectoren, der sender data til hjemmesiden. 
        placeholderTableData.innerHTML = CombinedHtmlTable;
    }).catch(err => {
        //console.log(err)
    })
}

//Funktion til at håndtere dropdownlisten i menuerne på hjemmesiden. Menuen hedder "Mine Opgaver"
function apiDroplist(apiUrl){
    //fetch kalder routen for API'et, dette sker via en promise, hvori der kan arbejdes med resultatet, når det er leveret. 
    fetch(apiUrl).then(response => {
        return response.json();
    }).then(data => {
        // Data omskrives til et json format
        jsonData = JSON.parse(data)
        //Oprette en placeholder for html elementet MineOpgaverMenuID
        var mineOpgaverMenu = document.getElementById("MineOpgaverMenuID");
         // Sletter de gamle resultater
        while (mineOpgaverMenu.firstChild) {
            mineOpgaverMenu.removeChild(mineOpgaverMenu.firstChild);
        }
        //Tilføjer forklarende linje, hvis der ingen data er på bruger at fremvise.
        if(jsonData.length == 0){
            jsonData = {0:{Titel: '[Ingen data på bruger]'}}
        }
        // Danner og tilføjer new list items
        for(item in jsonData){
            var listItem = document.createElement("li");
            var link = document.createElement("a");
            link.href = href = "./Cases.html?"+jsonData[item]["Case_ID"]+"#";
            link.textContent = jsonData[item].Titel;
            listItem.appendChild(link);
            mineOpgaverMenu.appendChild(listItem);
        }
    }).catch(err => {
        console.log(err)
    })
}

//Funktionen til at danne dropdown elementer i systemet. Dropdown elementerne hentes fra SQL databasen. 
function DropdownValuesToHtml(apiUrl,htmlID,AddTopLine,AddedOptionText){
    //fetch kalder routen for API'et, dette sker via en promise, hvori der kan arbejdes med resultatet, når det er leveret. 
    fetch(apiUrl).then(response => {
        return response.json();
    }).then(data => {
        // Data omskrives til et json format
        jsonData = JSON.parse(data)
        //Oprette en placeholder for html querySelector data-output-RowData
        let PlaceholderDropdown = document.querySelector(htmlID); //"#data-output-RowData"
        //Placeholder for htmlDropdown data
        CombinedHtmlDropdown = '<select id="'+htmlID.replace("#","")+'" name="Systemkategori">\n'
        //if-statement, der tillade muligheden for at filføje et start element til dropdown menuen.
        if(AddTopLine ==true){
            CombinedHtmlDropdown=CombinedHtmlDropdown+"\t<option>"+AddedOptionText+"</option>\n\n"
        }
        //placeholder for html table row data. 
        htmlTableRow = ''
        for(var key in jsonData) {
            htmlTableData = ''
            for (var key1 in jsonData[key]) {
                htmlTableData = htmlTableData+"\t<option>"+jsonData[key][key1]+"</option>\n\t\t"
            }
            htmlTableRow = htmlTableRow+htmlTableData+"\n"
        } 
        //Sammen skrivning af data
        CombinedHtmlDropdown = CombinedHtmlDropdown+htmlTableRow+"</select> \n"     
        //Overskrivelse af html elementet på hjemmesiden, således dropdown skabes på hjemmesiden. 
        PlaceholderDropdown.innerHTML = CombinedHtmlDropdown;
    }).catch(err => {
        console.log(err)
    })
}

//funtkion til at kalde data igennem API'et til databasen, og returnere resultatet i et json format, der kan arbejds videre med. 
function apiDataToJson(apiUrl){
    return fetch(apiUrl).then(response => {
        return response.json();
    }).then(data => {
        // Data omskrives til et json format
        jsonData = JSON.parse(data)
        return jsonData
    }).catch(err => {
        console.log(err)
    })
}

//Nedenstående er serien af EventListener der håndteres de forskellige event på siderne, når de bliver indlæst (load)
window.addEventListener('load', function(){
    //Sætter path, for at have en værdi der indeholde den nuværende indlæste fils pathnavn. 
    path = window.location.pathname

    //If-statement for paths der indeholder aktiverul (aktiverul.html)
    if(path.includes("aktiverul")){
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = "http://localhost:3000/api/Sp_GetSprintPeriods/"+currentWeekNumber+","+year
        fetch(apiUrl).then(response => {
            return response.json();
        }).then(data => {
        // Work with JSON data here
            //Formattering af resultatet for API'et, dette bliver oversat til json, som der kan arbejds videre med. 
            jsonData = JSON.parse(data)
            //Sætter 3 queruSelectors innerHTML til at være resultaterne for jsonData
            document.querySelector("#labelValue").innerHTML = "Sprint #: "+jsonData[0].WeekNumber
            document.querySelector("#SprintFraDato").innerHTML = "Fra dato: "+jsonData[0].StartDateOfWeeknumber.replace("T00:00:00.000Z","")
            document.querySelector("#SprintTilDato").innerHTML = "Til dato: "+jsonData[0].EndDateOfWeeknumber.replace("T00:00:00.000Z","")
            
            //Kalder funktionen til at danne html tables på hjemmesiden.
            apiDataToHtmlTable("http://localhost:3000/api/Sp_GetSprintData/"+currentWeekNumber+","+year,"#data-output-RowData")
        }).catch(err => {
            //Logger fejl. 
            console.log(err)
        })
    } 
    //If-statement for paths der indeholder nyopgave (nyopgave.html)
    else if(path.includes("nyopgave")){
        //Sætter url'erne der skal bruger i funktionen DropdownValuesToHtml, til at hente dropdown data til nyopgave siden. Dropdown modtager data fra sql databasen og udstiller dem. 
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[T_DropdownSystems]").replace(":ColumnName","[SystemName]")
        DropdownValuesToHtml(apiUrl,"#Systemkategori",true,"Vælg")
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[T_DropdownPriorities]").replace(":ColumnName","[Priority_Text]")
        DropdownValuesToHtml(apiUrl,"#prioritering",true,"Vælg")
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[T_DropdownSystems]").replace(":ColumnName","[SystemAnsvarligUdvikler]")
        DropdownValuesToHtml(apiUrl,"#opgpåtager",true,"Automatisk")
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[T_DropdownVisibility]").replace(":ColumnName","[Visibility_Text]")
        DropdownValuesToHtml(apiUrl,"#synlighed",false,"")
    
    } 
    //If-statement for paths der indeholder oversigt (oversigt.html)
    else if(path.includes("oversigt")){
        //Danner datatabellens indhold ved load
        apiDataToHtmlTable('http://localhost:3000/api/V_GetActiveSprintData',"#data-output-RowData")
        //Danner dropdown menu'ernes indhold ved load
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[T_DropdownStatus]").replace(":ColumnName","[StatusName]")
        DropdownValuesToHtml(apiUrl,"#DropdownCase_status_ID",true,"Vælg")
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[T_DropdownSystems]").replace(":ColumnName","[SystemName]")
        DropdownValuesToHtml(apiUrl,"#DropdownSystem_ID",true,"Vælg")
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[T_DropdownSystems]").replace(":ColumnName","[SystemAnsvarligUdvikler]")
        DropdownValuesToHtml(apiUrl,"#DropdownCase_Developer_ID",true,"Vælg")
        apiUrl="http://localhost:3000/api/Sp_GetDropdowns/:TableName/:ColumnName".replace(":TableName","[Admin].[V_SystemTableData]").replace(":ColumnName","[COLUMN_NAME]")
        DropdownValuesToHtml(apiUrl,"#DropdownColumnNames_ID",false,"Vælg")
    }
    //If-statement for paths der indeholder Cases (Cases.html)
    else if(path.includes("Cases")){
        //anvendes til at læse url stien, for at finde frem til den case der bliver arbejdet med: eks for at finde ?12: file:///C:/Users/Bokaj/OneDrive/Skrivebord/coding/TJANNS/Frontend/HTML/Cases.html?12#
        var queryString = location.search.substring(1)
        var a = queryString.split("|");
        var value1 = a[0];
        //Sætter html elementet Cases_Case_ID innerhtml til at være den værdi der stod i url'en
        document.getElementById("Cases_Case_ID").innerHTML = value1
        //funktion til at hente json data, hvorefter der køres en funktion på promised, til at ændre alle html elementers inner html / value på hjemmesiden for casen. 
        apiDataToJson("http://localhost:3000/api/Sp_GetSpecifikCasesReported/"+value1).then(function(results){
            document.getElementById("Cases_Case_Status").innerHTML = Object.values(results)[0].Case_Status
            document.getElementById("Cases_UserName").innerHTML = Object.values(results)[0].UserName
            document.getElementById("Cases_Email").value = Object.values(results)[0].Email
            document.getElementById("Cases_AddDate").innerHTML = Object.values(results)[0].AddDate
            document.getElementById("Cases_Titel").value = Object.values(results)[0].Titel
            document.getElementById("Cases_SystemName").value = Object.values(results)[0].SystemName
            document.getElementById("Cases_SystemDeveloper").value = Object.values(results)[0].SystemDeveloper
            document.getElementById("Cases_Public_or_Private").value = Object.values(results)[0].Public_or_Private
            document.getElementById("Cases_Error_importance").value = Object.values(results)[0].Error_importance
            document.getElementById("Cases_Error_Description").value = Object.values(results)[0].Error_Description
            document.getElementById("Cases_Attachment").value = Object.values(results)[0].Attachment
            //Sætter apiUrl for at skabe en tabel der indeholder comments på casen.
            apiUrl = ('http://localhost:3000/api/Sp_GetCasesComments/'+value1)
            apiDataToHtmlTable(apiUrl,"#data-output-RowData")
            //Sætter apiUrl for at skabe en tabel der indeholder attachments på casen.
            apiUrl = ('http://localhost:3000/api/Sp_GetCasesAttachment/'+value1)
            apiDataToHtmlTable(apiUrl,"#fileTable")
        });   
    }
    //If-statement for paths der indeholder index (index.html)
    else if(path.includes("index")){
        //Henter session data i form af navn, for at give en velkomst til brugeren, samt at gemme hvem der foretager handling i systemet, såsom at oprette sager, skrive comments, etc.
        document.getElementById("header2").innerHTML = 'Velkommen '+sessionStorage.getItem("systemUserName")
    }
    else{
        //Nothing for now
    }



})

//Nedenstående er serien af EventListener der håndteres de forskellige event på siderne, når de bliver klikket (onclick) i systemet 
//Sætter path, for at have en værdi der indeholde den nuværende indlæste fils pathnavn. 
path = window.location.pathname
//If-statement for paths der indeholder aktiverul (aktiverul.html)
if(path.includes("aktiverul")){
    //Hvis der trykkes på Forrige knappen i Aktive rul siden, så køres nedenstående EventListener
    document.getElementById("btnPrevious").addEventListener('click', function(){
        selectedPeriode = document.getElementById("labelValue").textContent.replace("Sprint #: ","")
        intSelectedPeriode = parseInt(selectedPeriode)
        intSelectedPeriode=intSelectedPeriode-1
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = 'http://localhost:3000/api/Sp_GetSprintPeriods/'+intSelectedPeriode+","+year
        fetch(apiUrl).then(response => {
            return response.json();
        }).then(data => {
            // Data omskrives til et json format
            jsonData = JSON.parse(data)
            //Sætter innerHTML for de 3 querySelector
            document.querySelector("#labelValue").innerHTML = "Sprint #: "+jsonData[0].WeekNumber
            document.querySelector("#SprintFraDato").innerHTML = "Fra dato: "+jsonData[0].StartDateOfWeeknumber.replace("T00:00:00.000Z","")
            document.querySelector("#SprintTilDato").innerHTML = "Til dato: "+jsonData[0].EndDateOfWeeknumber.replace("T00:00:00.000Z","")
            //Kalder funktionen
            apiDataToHtmlTable("http://localhost:3000/api/Sp_GetSprintData/"+intSelectedPeriode+","+year,"#data-output-RowData")
        }).catch(err => {
            console.log(err)
        })
    })
    //Hvis der trykkes på Next knappen i Aktive rul siden, så køres nedenstående EventListener
    document.getElementById("btnNext").addEventListener('click', function(){
        //Henter værdien i html elementet
        selectedPeriode = document.getElementById("labelValue").textContent.replace("Sprint #: ","")
        intSelectedPeriode = parseInt(selectedPeriode)
        intSelectedPeriode=intSelectedPeriode+1
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = 'http://localhost:3000/api/Sp_GetSprintPeriods/'+intSelectedPeriode+","+year
        fetch(apiUrl).then(response => {
            return response.json();
        }).then(data => {
            // Data omskrives til et json format
            jsonData = JSON.parse(data)
            //Sætter værdien i innerhteml for queryselectoren
            document.querySelector("#labelValue").innerHTML = "Sprint #: "+jsonData[0].WeekNumber
            document.querySelector("#SprintFraDato").innerHTML = "Fra dato: "+jsonData[0].StartDateOfWeeknumber.replace("T00:00:00.000Z","")
            document.querySelector("#SprintTilDato").innerHTML = "Til dato: "+jsonData[0].EndDateOfWeeknumber.replace("T00:00:00.000Z","")
            //Kalder funktionen
            apiDataToHtmlTable("http://localhost:3000/api/Sp_GetSprintData/"+intSelectedPeriode+","+year,"#data-output-RowData")
        }).catch(err => {
            console.log(err)
        })
    })
} 
//If-statement for paths der indeholder nyopgave (nyopgave.html)
else if(path.includes("nyopgave.html")){
    //Eventlistener onklick
    document.getElementById("knappen").addEventListener('click', function(){
        //Laver et array som indeholde elementerne på siden der skal opdateres.
        const inputDataIDs = ["Titel", "Systemkategori", "prioritering", "opgpåtager", "synlighed", "Fejlbeskrivelse"];
        var outDataIDs = [];
        for(var key in inputDataIDs) {
            result = document.getElementById(inputDataIDs[key]).value
            outDataIDs.push(result)
        }
        //Sætter variablerne der gemmer data, som skal bruges til apiUrl'en
        UserName = sessionStorage.getItem("systemUserName")
        Email = 'test@test.dk'
        Titel = outDataIDs[0]
        SystemName = outDataIDs[1]
        SystemDeveloper = outDataIDs[3]
        Public_or_Private =  outDataIDs[4]
        Error_importance = outDataIDs[2]
        Fejlbeskrivelse = outDataIDs[5]
        Attachment = '0'
        
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = "http://localhost:3000/api/post/Sp_PostNewCase/"+UserName+"/"+Email+"/"+Titel+"/"+SystemName+"/"+SystemDeveloper+"/"+Public_or_Private+"/"+Error_importance+"/"+Fejlbeskrivelse+"/"+Attachment+""
        fetch(apiUrl, {
            method: 'POST', //Vigtigt at method bliver sat til post, standard er get: https://reqbin.com/code/javascript
            //headers: {
            //    'Accept': 'application/json',
            //    'Content-Type': 'application/json'
            //},
        })
        .then(response => {
            response.json()
        })
        .then(response => {
            console.log(JSON.stringify(response))
        })
        //Giver en alert (popup til bruger) med opgaven er oprettet 
        alert("Opgaven er oprettet!");
        //Genlæser siden, skal laves om senere. // Noah tager denne.
        location.reload();
    }
)} 
//If-statement for paths der indeholder oversigt (oversigt.html)
else if(path.includes("oversigt")){
    //Eventlistener onklick
    document.getElementById("submitButton").addEventListener('click', function(){
        console.log("Submit pressed on Oversigt.html")
        //Laver et array som indeholde elementerne på siden der skal opdateres.
        const inputDataIDs = ["DropdownCase_status_ID", "LabelUserID", "DropdownSystem_ID", "DropdownCase_Developer_ID", "DropdownColumnNames_ID", "DropdownOrdering_ID"];
        var outDataIDs = [];
        for(var key in inputDataIDs) {
            result = document.getElementById(inputDataIDs[key]).value
            outDataIDs.push(result)
        }
        //Json værdierne anvendes til at kunne sortere tabellen ef i oversigts siden
        Case_Status = outDataIDs[0]
        if(Case_Status == "Vælg"){
            Case_Status=':Case_Status'
        }
        UserName = outDataIDs[1]
        if(UserName == ""){
            UserName=':UserName'
        }
        SystemName = outDataIDs[2]
        if(SystemName == "Vælg"){
            SystemName=':SystemName'
        }
        SystemDeveloper = outDataIDs[3]
        if(SystemDeveloper == "Vælg"){
            SystemDeveloper=':SystemDeveloper'
        }
        OrderBy = outDataIDs[4]
        if(OrderBy == "Vælg"){
            OrderBy=':OrderBy'
        }
        OrderByScending = outDataIDs[5]
        if(OrderByScending == "Vælg"){
            OrderByScending=':OrderByScending'
        }
        
        
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = ('http://localhost:3000/api/Sp_GetActiveSprintData_WithParams/'+Case_Status+'/'+UserName+'/'+SystemName+'/'+SystemDeveloper+'/'+OrderBy+'/'+OrderByScending+'')
        //Danner datatabellens indhold ved load
        apiDataToHtmlTable(apiUrl,"#data-output-RowData")
    })
}   
//If-statement for paths der indeholder Cases (Cases.html)
else if(path.includes("Cases")){
    //Eventlistener onklick
    document.getElementById("Cases_SaveCommentButton").addEventListener('click', function(){
        //Aflæser html elementernes textContent for at få case data
        Case_ID = document.getElementById("Cases_Case_ID").textContent
        Case_Comment = document.getElementById("Cases_CommentSectionText").value
        Case_UserName = sessionStorage.getItem("systemUserName")
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = ('http://localhost:3000/api/post/Sp_PostNewComment/'+Case_ID+'/'+Case_Comment+'/'+Case_UserName)
        fetch(apiUrl, {
            method: 'POST', //Vigtigt at method bliver sat til post, standard er get: https://reqbin.com/code/javascript
            //headers: {
            //    'Accept': 'application/json',
            //    'Content-Type': 'application/json'
            //},
        }
        )
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = ('http://localhost:3000/api/Sp_GetCasesComments/'+Case_ID)
        //Danner datatabellens med comments
        apiDataToHtmlTable(apiUrl,"#data-output-RowData")
        //Genlæser siden, skal laves om senere. 
        location.reload();
    })
    }
//If-statement for paths der indeholder login (login.html)
else if (path.includes("login.html")) {
    //clear alle variable der findes i den aktive session
    sessionStorage.clear();
    //Eventlistener onklick
    document.getElementById('loginknap').addEventListener('click', function() {
    //Sætter variablerne der skal bruges til login.
    const username = document.getElementById('loginname').value;
    const password = document.getElementById('loginpassword').value;

    //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
    //https://stackoverflow.com/questions/69037765/why-fetch-is-not-working-in-an-event-handler-function - FED JOKE!
    fetch('http://localhost:3000/api/post/Sp_PostloginInfo', {
        method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              UserName: username,
              Password: password
            })
    }).then(response => {
        return response.json();
    }).then(data => {
        //clear alle variable der findes i den aktive session
        sessionStorage.clear();
        // Data omskrives til et json format
        const jsonData = JSON.parse(data);
        //if-statement til at se om en bruger findes med credentals eller ej. 
        if (jsonData.length == 0) {
            console.log('Invalid login credentials');
            alert('Invalid login credentials');
            throw new Error('Invalid login credentials');
        } else if (jsonData.length == 1) {
            //console.log("Welcome, user: " + jsonData[0].UserName);
            sessionStorage.setItem("systemUserName", jsonData[0].UserName);
            window.location.href = "index.html";
        } else {
            console.log("Multiple users found with these credentials, access denied!");
            alert("Multiple users found with these credentials, access denied!");
            throw new Error("Multiple users found with these credentials, access denied!");
        }
    }).then(error =>{
        //console.log(error)
    })

}) 
    //Eventlistener onklick
    document.getElementById("signupknap").addEventListener('click', function(){
        //Laver et array som indeholde elementerne på siden der skal opdateres.
        const inputDataIDs = ["signupname", "Personnavn", "password"];
        var outDataIDs = [];
        for(var key in inputDataIDs) {
            result = document.getElementById(inputDataIDs[key]).value
            outDataIDs.push(result)
        }
        //Sætter variablerne der gemmer data, som skal bruges til apiUrl'en
        signupName=outDataIDs[0]
        Personnavn=outDataIDs[1]
        password=outDataIDs[2]
        //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
        apiUrl = "http://localhost:3000/api/post/Sp_PostNewUser/"+Personnavn+"/"+""+signupName+"/"+password
        fetch(apiUrl, {
            method: 'POST', //Vigtigt at method bliver sat til post, standard er get: https://reqbin.com/code/javascript
            //headers: {
            //    'Accept': 'application/json',
            //    'Content-Type': 'application/json'
            //},
        }).then(response => {
            return response.json();
        }).then(data => {
            //Opretter en bruger, hvis der ikke allerede findes en med samme credentials
            if(data[0]["message"] == 'Bruger findes allerede'){
                alert("Bruger findes allerede!");
                location.reload();
            }else if(data[0]["message"] == 'Bruger oprettes'){
                alert("Bruger oprettes!");
                location.reload();
            }else{
                //Nothing for now
            }
        }).then(error =>{
            //console.log(error)
        })
        //.then(response => console.log(JSON.stringify(response)))
    })
    
}

//-------------------------------------------------------------------------------------------------------------------------------------
//funktion til at finde ud af hvilke row i en tabel brugeren trykker på, hvorefter data for childNoden findes (Finder text indhold for et lavere niveua i en tabel)
function myFunction(x) { //https://stackoverflow.com/questions/1207939/adding-an-onclick-event-to-a-table-row
    //Læser hvilken linje der trykkes på ud fra nuværende sortering
    currentRow = document.getElementById(x.id) 
    
    //Finder tabel <tr> id og går et child niveua new for at finde <Td>'s data.
    currentRowFirstColumnValue = currentRow.childNodes[1].textContent //https://www.w3schools.com/jsref/prop_node_childnodes.asp
    //console.log(currentRowFirstColumnValue)

    //how to parse data between sites: https://lage.us/Javascript-Pass-Variables-to-Another-Page.html
    window.location.href = "./Cases.html?"+currentRowFirstColumnValue;
    
}



//-------------------------------------------------------------------------------------------------------------------------------------
/*https://www.youtube.com/watch?v=aj9X7mbMg1U&ab_channel=EasyWeb*/
//Funktion til at skujle og vise mineopgaver i menuen
function showHideMineopgaver(FirstElementID,SecondElementID) {
    //console.log("pressed")
    const button = document.getElementById(FirstElementID);
    const list = document.getElementById(SecondElementID);
    list.style.display = 'block';
    //initialisere apiUrl, denne bliver brugt løbende til at sætte routen der skal spørgers om ved API'et
    apiUrl="http://localhost:3000/api/Sp_GetMineopgaver/"+sessionStorage.getItem("systemUserName")
    //Laver en dropdown til menuen med de opgaver en medarbejder selv har indberettet.
    apiDroplist(apiUrl)
    button.addEventListener("click", (event) => {
        if (list.style.display == "none"){
            list.style.display = "block";
        }else{
            list.style.display = "none"
        }
    })
    }

//funktion til at ændre farverne i systemet. Farvevalg bliver gemt i browser localstorage til næste gang en bruger logger ind.
function NewColours(){
    dropdownValue = document.getElementById("background-color-select")
    background = document.getElementById("indberetFormularID")
    if(dropdownValue.value == 'Grøn'){
        selectedColor = "linear-gradient(120deg,rgb(131, 200, 187), #17b01c)"
        background.style.background = selectedColor;
    }else if(dropdownValue.value == 'Lyserød'){
        selectedColor = "linear-gradient(120deg,rgb(131, 200, 187), #b8256c)"
        background.style.background = selectedColor;
    }else if(dropdownValue.value == 'Blå'){
        selectedColor = "linear-gradient(120deg,rgb(131, 200, 187), #2312a8)"
        background.style.background = selectedColor;  
    }else{ 
        selectedColor = "linear-gradient(120deg,rgb(131, 200, 187), #00B08D)"
        background.style.background = selectedColor;  
    }
    localStorage.setItem('bgColor', selectedColor);
}

//-------------------------------------------------------------------------------------------------------------------------------------
//Læser stored variabler og loader dem. I dette tilfælde background farver. 
var storedColor = localStorage.getItem("bgColor");
background = document.getElementById("indberetFormularID")
background.style.background = storedColor;

//Funktion der popup med om man gerne vil logge ud
function confirmLogout(event) {
    event.preventDefault(); // Forhindrer standardadfærden for ankertagget
    const userConfirmed = confirm('Er du sikker på, at du vil logge ud?');
    if (userConfirmed) {
      window.location.href = 'login.html'; // Sender bruger til login-siden, hvis brugeren bekræfter
    }
}
//funktion til at confirm valg
  function confirmSubmit() {
    var form = document.querySelector(".form-alle");
    if (!form.checkValidity()) {
        return false;
    }
    
    if (!confirm('Er du sikker på, at du vil indberette?')) {
        return false;
    }
    
    return true;
}
//funktion til at confirm valg
function confirmSubmit(event) {
    document.getElementById("submitButton").addEventListener("click", confirmSubmit);
    if (!confirm("Er du sikker?")) {
      event.preventDefault();
    }
  }

function selectFilesToUpload(){
    
}

//eventhandler handler med onclick, funktionaliteten til at kunne gemmer filer i systemet på case vinduet.
//Pt. kan den ikke flyttes op til andet, har ikke mere tid, så kan ikke lige undersøge hvorfor xD
path = window.location.pathname;
if (path.includes("Cases.html")) {
      //Danner objecter der indholder data fra html ID'erne
    const form = document.getElementById('uploadForm');
    const fileInput = document.getElementById('filesToUpload');
    //Eventlisterner på objectet fileInput
  
    document.getElementById('uploadbutton').addEventListener('click', (event) => {
        event.preventDefault();
        const files = fileInput.files;
        const formData = new FormData(form);
        value1 = document.getElementById("Cases_Case_ID").innerHTML
        var Case_ID = value1;
        for (let i = 0; i < files.length; i++) {
            const file = files[i];
            formData.append('uploadedFiles', file);
            url = "http://localhost:3000/api/post/Filetransfer/"+Case_ID+"/"+sessionStorage.getItem("systemUserName")+""
            console.log(url)
            fetch(url, {
                method: "POST",
                body: formData
            })
        }
        alert("Filerne uploades!");
        location.reload();
    });
}
