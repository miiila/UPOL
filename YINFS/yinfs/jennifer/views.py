from django.shortcuts import render

# Create your views here.

from .models import Section, Publication, PublicationType, Teaching, Education, Position, Award


def index(request):
    context = {
        'title': 'Jennifer Doe - homepage',
        'menuItems': Section.objects.all(),
        'publications': Publication.objects.all(),
        'types': PublicationType.objects.all(),
        'teachings': Teaching.objects.all(),
        'awards': Award.objects.all(),
        'positions': Position.objects.all(),
        'educations': Education.objects.all()
    }

    return render(request, 'index.html', context)