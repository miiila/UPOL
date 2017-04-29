from django.contrib import admin

# Register your models here.
from .models import Project, Publication, PublicationType, Section, Education, Position, Award, Teaching

admin.site.register(Section)
admin.site.register(Project)
admin.site.register(Publication)
admin.site.register(PublicationType)
admin.site.register(Education)
admin.site.register(Position)
admin.site.register(Award)
admin.site.register(Teaching)