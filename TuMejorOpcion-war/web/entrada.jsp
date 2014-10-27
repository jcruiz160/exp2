<%@page import="facebook4j.ResponseList"%>
<%@page import="java.util.List"%>
<%@page import="facebook4j.Facebook"%>
<%@page import="facebook4j.Friend"%>
<%@page import="facebook4j.Like"%>
<!DOCTYPE html>
<!--[if lte IE 8]>               <html class="ie8 no-js" lang="en">    <![endif]-->
<!--[if lte IE 10]>				 <html class="ie10 no-js" lang="en">   <![endif]-->
<!--[if !IE]>-->				 <html class="not-ie no-js" lang="en"> <!--<![endif]-->
    <head>

        <!-- Google Web Fonts
  ================================================== -->
        <link href='http://fonts.googleapis.com/css?family=Roboto:100,300,300italic,400,700|Julius+Sans+One|Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>

        <!-- Basic Page Needs
  ================================================== -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>Accio</title>	

        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Favicons
        ================================================== -->
        <link rel="shortcut icon" href="images/favicon.png">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
        <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">	

        <!-- Mobile Specific Metas
  ================================================== -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

        <!-- CSS
  ================================================== -->
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/grid.css" />
        <link rel="stylesheet" href="css/layout.css" />
        <link rel="stylesheet" href="css/fontello.css" />
        <link rel="stylesheet" href="css/animation.css" />

        <link rel="stylesheet" href="js/layerslider/css/layerslider.css" />
        <link rel="stylesheet" href="js/flexslider/flexslider.css" />
        <link rel="stylesheet" href="js/fancybox/jquery.fancybox.css" />

        <!-- HTML5 Shiv
        ================================================== -->
        <script src="js/jquery.modernizr.js"></script>

    </head>
    <body data-spy="scroll" data-target="#navigation" class="page-template">


        <!-- - - - - - - - - - - - - - Loader - - - - - - - - - - - - - - - - -->	


        <div class="loader"></div><!--/ .loader-->


        <!-- - - - - - - - - - - - - end Loader - - - - - - - - - - - - - - - -->	


        <!-- - - - - - - - - - - - - - Header - - - - - - - - - - - - - - - - -->	


        <header id="header">

            <div class="header-in">

                <h1 id="logo"><a href="index.html">TuMejorOpcion</a></h1>

                <a id="responsive-nav-button" class="responsive-nav-button" href="#"></a>

                <nav id="navigation" class="navigation">

                    <ul>
                        <li><a href="#">Cuenta</a>
                            <ul>
                                <li><a href="./historial.html">Registro de bonos comprados</a></li>
                                <li><a href="./logout.html">Cerrar sesion</a></li>
                            </ul>
                        </li>

                    </ul>

                </nav><!--/ #navigation-->

            </div><!--/ .header-in-->

        </header><!--/ #header-->


        <!-- - - - - - - - - - - - - end Header - - - - - - - - - - - - - - - -->


        <!-- - - - - - - - - - - - - - Wrapper - - - - - - - - - - - - - - - - -->


        <div id="wrapper">

            <section class="section">

                <div id="content">

                    <div class="container">

                        <div class="row">
                            <div class="col-xs-12">
                                <hgroup class="section-title align-center">
                                    <h1>Bonos</h1>
                                </hgroup>	
                            </div>
                        </div><!--/ .row-->			

                        <div class="row">

                            <section id="main" class="col-md-8">

                                <article class="entry main-entry single">



                                    <h2 class="entry-title">Compra un bono</h2><!--/ .entry-title-->
                                    <div class="wisget widget_categories">
                                        <div id="main2" class="col-md-8">

                                            <form action="./funcionalidadBonos" method="get">
                                                <br>
                                                Seleccione el amigo a quien desea dar el bono:
                                                <select name="SelectFriend" value="seleccionarAmigo" multiple="multiple">
                                                    <%
                                                        Facebook fb = (Facebook) request.getSession().getAttribute("facebook");

                                                        List<Friend> friends = fb.getFriends();

                                                        for (int i = 0; i < friends.size(); i++) {
                                                            Friend actualFriend = friends.get(i);


                                                    %> 
                                                    <option value="<%=actualFriend.getName()%>"><%=actualFriend.getName()%></option>
                                                    <%

                                                        }
                                                    %>
                                                </select>
<div class="col-md-12">
                                            Ingresar valor (debe ser mayor a COP $10.000):
                                            <input type="text"  name="messagecomprar"></input>
                                            <br><Br>
                                            <input type="submit" class="button turquoise large opacityRun" name="comprarBonos" value="Comprar Bonos" />
                                        </div>
                                        </div>
                                        <div class="col-md-4">
                                            <br>
                                            Seleccione la tienda:
                                            <select name="Select" value="seleccionarTienda" multiple="multiple">
                                                 <% 
        List<Like> likes = fb.getUserLikes();

        for (int i = 0; i < likes.size(); i++) {
            Like actualLike = likes.get(i);

            if (actualLike.getCategory().equals("Clothing")) {
                %> 
                <option value="<%=actualLike.getName()%>"><%=actualLike.getName()%></option>
                <%
            }
        }
        %>
                                            </select>
                                        </div>
                                        
                                        </form>

                                        <br><br>
                                        </li></ul>
                                    </div><!--/ .entry-body-->

                                </article><!--/ .entry-->									

                            </section><!--/ #main-->

                            <aside id="sidebar" class="col-md-4">

                                <div class="widget widget_recent_posts">
                                    <section>
                                        <article class="entry"> 
                                            <img alt="" src="http://www.digitalexposure.co.uk/blog/wp-content/uploads/2011/04/clothingphotography03.jpg" />
                                        </article><!--/ .entry-->
                                    </section>

                                    <form action="./redimirBono" method="get">
                                        <h2 class="entry-title">Redime un bono</h2><!--/ .entry-title-->
                                        <input type="text"  name="message1"></input>
                                        <BR><BR>
                                        <input type="submit" class="button turquoise large opacityRun" name="comprarBonos" value="Redimir Bonos" />

                                    </form>

                                </div><!--/ .widget-->
                            </aside><!--/ #sidebar-->		

                        </div><!--/ .row-->

                    </div><!--/ .container-->

                </div><!--/ #content-->

            </section><!--/ .section-->


            <!-- - - - - - - - - - - - - - Footer - - - - - - - - - - - - - - - - -->


            <footer id="footer">

                <div class="bottom-footer clearfix">

                    <div class="container">

                        <div class="row">

                            <div class="col-sm-6">

                                <div class="copyright">
                                    Copyright © 2014. <a target="_blank" href="#">Camilo Alfonso</a>. All rights reserved
                                </div><!--/ .cppyright-->

                            </div>

                            <div class="col-sm-3 col-sm-offset-3">

                                <div class="developed">
                                    Developed by <a target="_blank" href="http://arquisoft.bitrix24.com/">Los Aristogatos</a>
                                </div><!--/ .developed-->

                            </div>

                        </div><!--/ .row-->

                    </div><!--/ .container-->

                </div><!--/ .bottom-footer-->	

            </footer><!--/ #footer-->


            <!-- - - - - - - - - - - - - end Footer - - - - - - - - - - - - - - - -->


        </div><!--/ #wrapper-->


        <!-- - - - - - - - - - - - - end Wrapper - - - - - - - - - - - - - - - -->


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
        <!--[if lt IE 9]>
                <script src="js/respond.min.js"></script>
                <![endif]-->
        <script src="js/jquery.queryloader2.js"></script>
        <script src="js/jquery.easing.1.3.min.js"></script>
        <script src="js/jquery.cycle.all.min.js"></script>
        <script src="js/jquery.smoothscroll.js"></script>
        <script src="js/waypoints.min.js"></script>
        <script src="twitter/jquery.tweet.js"></script>
        <script src="js/fancybox/jquery.fancybox.pack.js"></script>
        <script src="js/config.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>