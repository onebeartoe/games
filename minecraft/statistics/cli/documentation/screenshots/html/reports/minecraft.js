
function hideElement(elementId)
{
    element = document.getElementById(elementId)
    
    element.style.display = "none";
}

function showElement(elementId)
{
    element = document.getElementById(elementId)
    
    element.style.display = "block";    
}

function hideElementAndShowRicky(elementId)
{
    hideElement(elementId)
    
    showElement("ricky")
    
    var elem = document.querySelector("video");
    
    elem.play();
}
