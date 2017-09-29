

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
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/user/**',          access: ['ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER']],
	[pattern: '/groupe/**', access: ['ROLE_ADMIN','ROLE_MODERATOR']],
	[pattern: '/poi/**', access: ['ROLE_ADMIN','ROLE_MODERATOR']],
	[pattern: '/image/**', access: ['ROLE_ADMIN','ROLE_MODERATOR']],
	[pattern: '/groupe/show/**', access: ['ROLE_USER']],
	[pattern: '/poi/show/**', access: ['ROLE_USER']],
	[pattern: '/image/show/**', access: ['ROLE_USER']],
	[pattern: '/groupe/index', access: ['ROLE_USER']],
	[pattern: '/poi/index', access: ['ROLE_USER']],
	[pattern: '/image/index', access: ['ROLE_USER']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

