

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.mbds.poi.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.mbds.poi.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.mbds.poi.Role'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

port = 7001
//serverUrl = "http://localhost:" + port
//serverApache = "C:/wamp64/www/POI"
grails.serverUrl = "http://localhost:" + port
grails.serverApache="C:/wamp64/www/myapp"
images.groupes.url=grails.serverUrl + "/myapp/groupes/"
images.groupes.path=grails.serverApache + "/groupes/"
images.pois.url=grails.serverUrl + "/myapp/pois/"
images.pois.path=grails.serverApache + "/pois/"
images.test.path = grails.serverApache + "/test/"
images.test.url = grails.serverUrl + "/test/"
API_KEY="AIzaSyDm0dsqeJBhhE_2AjoKoKwiPmol5SEoBMs"
