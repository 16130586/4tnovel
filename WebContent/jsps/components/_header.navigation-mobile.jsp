<style>
.dropdown-menu {
	position: relative;
	color: inherit;
	background-color: red;
}

.dropdown-menu .dropdown-btn {
	display: block;
}
.dropdown-menu .dropdown-content--right , .dropdown-menu .dropdown-content--bottom{
	width: 8rem;
}
.dropdown-menu .dropdown-content--bottom {
	transition: all .7s;
	position: absolute;
	top: 0rem;
	left: 0px;
	display: none;
	z-index: 100000;
}

.dropdown-menu .dropdown-content--right {
	transition: all .7s;
	position: absolute;
	top: 0rem;
	left: 100%; display : none;
	z-index: 100000;
	display: none;
}

.dropdown-menu:hover .dropdown-content--bottom {
	display: block;
}

.dropdown-menu:hover .dropdown-menu:hover .dropdown-content--right {
	display: inline-block;
	width: 16rem;
}

.vertical-menu--showcase, .horizontal-menu--showcase {
	padding: 0rem;
	list-style: none;
}

.vertical-menu--showcase .menu-item, .horizontal-menu--showcase .menu-item
	{
	font-size: .7rem;
	font-weight: 300;
	border-bottom: 1px solid transparent;
	transition: all .3s;
}

.vertical-menu--showcase .menu-item:first-child {
	padding-top: .5rem;
}

.vertical-menu--showcase .menu-item:hover {
	
}
.horizontal-menu--showcase .menu-item{
	display:inline-block;
}
</style>
<nav class="navigation-mobile" id="navigation-mobile">
	<div class="dropdown-menu u-inline-block">
		<a class="dropdown-btn btn btn--orange-ghost"> <i
			class="fas fa-bars 2x"></i>
		</a>
		<div class="dropdown-content--bottom">
			<ul class="vertical-menu--showcase">
				<li class="menu-item"><a href="#">Trang chu</a></li>
				<li class="menu-item"><a href="#">Tim kiem</a></li>
				<li class="menu-item">
					<div class="dropdown-menu">
						<a class="dropdown-btn btn btn--orange-ghost">Tac pham</a>
						<div class="dropdown-content--right">
							<ul class="horizontal-menu--showcase">
								<li class="my-menu-item full-width btn--orange-ghost"><a
									href="#" class="link-btn" target="_blank">Sang tac</a></li>
								<li class="my-menu-item full-width btn--orange-ghost"><a
									href="#" class="link-btn" target="_blank">Truyen dich</a></li>
							</ul>
						</div>
					</div>
				</li>
				<li class="menu-item">
					<div class="dropdown-menu">
						<a class="dropdown-btn btn btn--orange-ghost">Tam su</a>
						<div class="dropdown-content--right">
							<ul class="horizontal-menu--showcase">
								<li class="my-menu-item full-width btn--orange-ghost"><a
									href="#" class="link-btn" target="_blank">Nhom</a></li>
								<li class="my-menu-item full-width btn--orange-ghost"><a
									href="#" class="link-btn" target="_blank">Nguoi la</a></li>
							</ul>
						</div>
					</div>
				</li>
				<li class="menu-item"><a href="#">Thao luan</a></li>
				<li class="menu-item"><a href="#">Dang nhap</a></li>
				<li class="menu-item"><a href="#">Tro giup</a></li>
			</ul>
		</div>
	</div>
</nav>
