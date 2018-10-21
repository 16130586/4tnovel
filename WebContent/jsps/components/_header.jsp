<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#header--on-top {
	background-color: blue;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

#navigation-mobile {
	display: none;
}

#navigation-desktop {
	display: inline-block;
}

#user-functions {
	
}

.main-logo--top {
	display: inline-block;
}

.radius-logo {
	border-radius: 50%;
}

@media only screen and (max-width: 600px) {
	#navigation-desktop {
		display: none;
	}
	#navigation-mobile {
		display: inline-block;
	}
}
</style>
<header id="header--on-top" class="header--on-top">
	<%@ include file="/jsps/components/_header.navigation-mobile.jsp"%>
	<%@ include file="/jsps/components/_header.navigation-desktop.jsp"%>
	<%@ include file="/jsps/components/_header.user-functions.jsp"%>
</header>