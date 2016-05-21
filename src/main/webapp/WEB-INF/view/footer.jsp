<footer id="footer"><!--Footer-->
  <div class="footer-top">
    <div class="container">
      <div class="row">
        <div class="col-sm-2">
          <div class="companyinfo">
            <h2 translate>partnersAndSponsors.title.label</h2>
            <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>--%>
          </div>
        </div>
        <div class="col-sm-7">
          <div class="col-sm-3">
            <div class="video-gallery text-center">
              <a href="http://www.coca-cola.ba/bs/home/">
                <div class="iframe-img">
                  <img src="resources/images/home/coca-cola-logo.png" alt="" />
                </div>
                <div class="overlay-icon">
                  <i class="fa fa-play-circle-o"></i>
                </div>
              </a>
              <p>Coca Cola</p>
            </div>
          </div>

          <div class="col-sm-3">
            <div class="video-gallery text-center">
              <a href="http://www.logosoft.ba/">
                <div class="iframe-img">
                  <img src="resources/images/home/logosoft.jpg" alt="" />
                </div>
                <div class="overlay-icon">
                  <i class="fa fa-play-circle-o"></i>
                </div>
              </a>
              <p>Logosoft</p>
            </div>
          </div>

          <div class="col-sm-3">
            <div class="video-gallery text-center">
              <a href="http://vakuba.ba/">
                <div class="iframe-img">
                  <img src="resources/images/home/vakufskaBanka.jpg" alt="" />
                </div>
                <div class="overlay-icon">
                  <i class="fa fa-play-circle-o"></i>
                </div>
              </a>
              <p>Vakufska Banka dd</p>
            </div>
          </div>

          <div class="col-sm-3">
            <div class="video-gallery text-center">
              <a href="https://www.facebook.com/skittles">
                <div class="iframe-img">
                  <img src="resources/images/home/skittles.jpg" alt="" />
                </div>
                <div class="overlay-icon">
                  <i class="fa fa-play-circle-o"></i>
                </div>
              </a>
              <p>Skittles</p>
            </div>
          </div>
        </div>
        <div class="col-sm-3">
          <div class="address">
            <img src="resources/images/home/map.png" alt="" />
            <p>Zmaja od Bosne bb, Sarajevo (Bosna i Hercegovina)</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="footer-widget">
    <div class="container">
      <div class="row">
        <div class="col-sm-2">
          <div class="single-widget">
            <h2 translate>footer.aboutus.title.label</h2>
            <ul class="nav nav-pills nav-stacked">
              <li><a href="" translate>footer.contacts.title.label</a></li>
              <li><a href="" translate>footer.basicInfo.title.label</a></li>
              <li><a href="" translate>footer.businessHours.title.label</a></li>
              <li><a href="" translate>footer.hire.title.label</a></li>
            </ul>
          </div>
        </div>
        <div class="col-sm-2">
          <div class="single-widget">
            <h2 translate>footer.service.title.label</h2>
            <ul class="nav nav-pills nav-stacked">
              <li><a href="" translate>footer.pretpremijera.title.label</a></li>
              <li><a href="" translate>footer.family.title.label</a></li>
              <li><a href="" translate>footer.birthdays.title.label</a></li>
              <li><a href="" translate>footer.cineLady.title.label</a></li>
              <li><a href="" translate>footer.cineMen.title.label</a></li>
              <li><a href="" translate>footer.cineWednestay.title.label</a></li>
            </ul>
          </div>
        </div>
        <div class="col-sm-2">
          <div class="single-widget">
            <h2 translate>footer.loyalityProgram.title.label</h2>
            <ul class="nav nav-pills nav-stacked">
              <li><a href="" translate>footer.starClubClassic.title.label</a></li>
              <li><a href="" translate>footer.starClubBussines.title.label</a></li>
              <li><a href="" translate>footer.student.title.label</a></li>
              <li><a href="" translate>footer.seniorClub.title.label</a></li>
            </ul>
          </div>
        </div>
        <div class="col-sm-2">
          <div class="single-widget">
            <h2 translate>footer.prices.title.label</h2>
            <ul class="nav nav-pills nav-stacked">
              <li><a href=""translate>footer.regular.title.label</a></li>
              <li><a href=""translate>footer.group.title.label</a></li>
              <li><a href=""translate>footer.school.title.label</a></li>
              <li><a href=""translate>footer.hospitality.title.label</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="footer-bottom">
    <div class="container">
      <div class="row">
      </div>
    </div>
  </div>

</footer><!--/Footer-->

<%--form for logout--%>
<form action="/j_spring_security_logout" method="post" id="logoutForm">
  <input type="hidden" name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>
