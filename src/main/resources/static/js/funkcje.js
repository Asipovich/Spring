(function() {
    //copyright JGA 2013 under MIT License
    var monster={set:function(e,t,n,r){var i=new Date,s="",o=typeof t,u="";r=r||"/",n&&(i.setTime(i.getTime()+n*24*60*60*1e3),s="; expires="+i.toGMTString());if(o==="object"&&o!=="undefined"){if(!("JSON"in window))throw"Bummer, your browser doesn't support JSON parsing.";u=JSON.stringify({v:t})}else u=escape(t);document.cookie=e+"="+u+s+"; path="+r},get:function(e){var t=e+"=",n=document.cookie.split(";"),r="",i="",s={};for(var o=0;o<n.length;o++){var u=n[o];while(u.charAt(0)==" ")u=u.substring(1,u.length);if(u.indexOf(t)===0){r=u.substring(t.length,u.length),i=r.substring(0,1);if(i=="{"){s=JSON.parse(r);if("v"in s)return s.v}return r=="undefined"?undefined:unescape(r)}}return null},remove:function(e){this.set(e,"",-1)},increment:function(e,t){var n=this.get(e)||0;this.set(e,parseInt(n,10)+1,t)},decrement:function(e,t){var n=this.get(e)||0;this.set(e,parseInt(n,10)-1,t)}};

    if (monster.get('cookieinfo') === 'true') {
        return false;
    }

    var container = document.createElement('div'),
        link = document.createElement('a');

    container.setAttribute('id', 'cookieinfo');
    container.setAttribute('class', 'cookie-alert');
    container.innerHTML = '<h6>This website uses cookies</h6><p>We use information saved by using cookies to ensure maximum convenience in using our website. <a class="cookielink" href="https://skrypt-cookies.pl/czym-sa-ciasteczka">Read more</a></p>';

    link.setAttribute('href', '#');
    link.setAttribute('id', 'cookie');
    link.setAttribute('title', 'Zamknij');
    link.innerHTML = 'x';

    function clickHandler(e) {
        if (e.preventDefault) {
            e.preventDefault();
        } else {
            e.returnValue = false;
        }

        container.setAttribute('style', 'opacity: 1');

        var interval = window.setInterval(function() {
            container.style.opacity -= 0.01;

            if (container.style.opacity <= 0.02) {
                document.body.removeChild(container);
                window.clearInterval(interval);
            }
        }, 4);
    }

    if (link.addEventListener) {
        link.addEventListener('click', clickHandler);
    } else {
        link.attachEvent('onclick', clickHandler);
    }

    container.appendChild(link);
    document.body.appendChild(container);

    monster.set('cookieinfo', 'true', 365);

    return true;
})();